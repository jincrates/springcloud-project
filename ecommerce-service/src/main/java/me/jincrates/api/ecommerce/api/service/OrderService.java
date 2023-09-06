package me.jincrates.api.ecommerce.api.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jincrates.api.ecommerce.api.service.request.OrderCreateServiceRequest;
import me.jincrates.api.ecommerce.api.service.response.OrderListPageServiceResponse;
import me.jincrates.api.ecommerce.api.service.response.OrderServiceResponse;
import me.jincrates.api.ecommerce.domain.member.Member;
import me.jincrates.api.ecommerce.domain.member.MemberRepository;
import me.jincrates.api.ecommerce.domain.order.Order;
import me.jincrates.api.ecommerce.domain.order.OrderProduct;
import me.jincrates.api.ecommerce.domain.order.OrderRepository;
import me.jincrates.api.ecommerce.domain.product.Product;
import me.jincrates.api.ecommerce.domain.product.ProductRepository;
import me.jincrates.api.ecommerce.domain.stock.Stock;
import me.jincrates.api.ecommerce.domain.stock.StockRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final StockRepository stockRepository;

    @Transactional
    public OrderServiceResponse order(OrderCreateServiceRequest request, String email) {
        Member member = memberRepository.findByEmail(email)
            .orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다. email=" + email));

        Product product = productRepository.findById(request.getProductId())
            .orElseThrow(() -> new EntityNotFoundException(
                "상품을 찾을 수 없습니다. productId=" + request.getProductId()));

        // 재고 차감
        deductStockQuantity(product, request.getQuantity());

        List<OrderProduct> orderProducts = new ArrayList<>();
        OrderProduct orderProduct = OrderProduct.create(product, request.getQuantity());
        orderProducts.add(orderProduct);

        Order order = Order.create(member, orderProducts);
        orderRepository.save(order);

        // 결제로직
        order.progress();
        order.success();

        return OrderServiceResponse.of(order);
    }

    @Transactional(readOnly = true)
    public OrderListPageServiceResponse getOrderListPage(String email, Pageable pageable) {
        Page<Order> orders = orderRepository.findOrdersPage(email, pageable);

        List<OrderServiceResponse> responses = orders.stream().map(OrderServiceResponse::of).toList();

        return OrderListPageServiceResponse.builder()
            .pageNo(orders.getPageable().getPageNumber())
            .hasNext(orders.hasNext())
            .contents(responses)
            .build();
    }

    @Transactional(readOnly = true)
    public boolean validateOrder(UUID orderId, String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다. email=" + email));

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("주문을 찾을 수 없습니다. orderId=" + orderId));

        Member orderMember = order.getMember();

        return Objects.equals(member.getEmail(), orderMember.getEmail());
    }

    @Transactional
    public void cancel(UUID orderId) {
        // TODO: 324
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("주문을 찾을 수 없습니다. orderId=" + orderId));
        order.cancel();;
    }

    private void deductStockQuantity(Product product, int quantity) {
        Stock stock = stockRepository.findByProduct(product);

        // 재고 차감 시도
        if (stock.isQuantityLessThan(quantity)) {
            log.warn("재고가 부족한 상품이 있습니다. productId={}, stock.quantity={}", product.getId(),
                stock.getQuantity());
            throw new IllegalArgumentException("재고가 부족한 상품이 있습니다.");
        }

        stock.deductQuantity(quantity);
    }

}
