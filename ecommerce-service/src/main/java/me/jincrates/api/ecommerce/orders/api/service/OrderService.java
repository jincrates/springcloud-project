package me.jincrates.api.ecommerce.orders.api.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jincrates.api.ecommerce.carts.application.port.CartPort;
import me.jincrates.api.ecommerce.carts.domain.Cart;
import me.jincrates.api.ecommerce.carts.domain.CartProduct;
import me.jincrates.api.ecommerce.members.application.port.MemberPort;
import me.jincrates.api.ecommerce.members.domain.Member;
import me.jincrates.api.ecommerce.orders.api.service.request.OrderCreateServiceRequest;
import me.jincrates.api.ecommerce.orders.api.service.response.OrderServiceResponse;
import me.jincrates.api.ecommerce.orders.domain.Order;
import me.jincrates.api.ecommerce.orders.domain.OrderProduct;
import me.jincrates.api.ecommerce.orders.domain.OrderRepository;
import me.jincrates.api.ecommerce.products.adapter.database.ProductRepository;
import me.jincrates.api.ecommerce.products.adapter.database.StockRepository;
import me.jincrates.api.ecommerce.products.domain.Product;
import me.jincrates.api.ecommerce.products.domain.Stock;
import me.jincrates.api.global.common.response.PageResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final MemberPort memberPort;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final StockRepository stockRepository;
    private final CartPort cartPort;

    @Transactional
    public OrderServiceResponse order(OrderCreateServiceRequest request, String email) {
        Member member = memberPort.findMemberByEmail(email);

        Product product = getProductById(request.getProductId());

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

    @Transactional
    public OrderServiceResponse orders(List<OrderCreateServiceRequest> requests, String email) {
        Member member = memberPort.findMemberByEmail(email);

        List<OrderProduct> orderProducts = new ArrayList<>();
        for (OrderCreateServiceRequest request : requests) {
            Product product = getProductById(request.getProductId());
            OrderProduct orderProduct = OrderProduct.create(product, request.getQuantity());
            orderProducts.add(orderProduct);
        }

        Order order = Order.create(member, orderProducts);
        orderRepository.save(order);

        // 결제로직
        order.progress();
        order.success();

        return OrderServiceResponse.of(order);
    }

    @Transactional
    public OrderServiceResponse orderFromCart(String email) {
        Member member = getMemberByEmail(email);

        Cart cart = cartPort.findCartByMemberId(member.getId());

        List<OrderProduct> orderProducts = new ArrayList<>();

        for (CartProduct cartProduct : cart.getCartProducts()) {
            OrderProduct orderProduct = OrderProduct.create(cartProduct.getProduct(),
                    cartProduct.getQuantity());
            orderProducts.add(orderProduct);

            // 재고감소
            Stock stock = getStockByProduct(cartProduct.getProduct());
            stock.deductQuantity(cartProduct.getQuantity());
        }

        Order order = Order.create(member, orderProducts);
        orderRepository.save(order);

        // 결제로직
        order.progress();
        order.success();

        // 장바구니 제거
        cartPort.deleteAllCartProduct(cart.getCartProducts());

        return OrderServiceResponse.of(order);
    }

    public PageResponse<?> getOrders(String email, Pageable pageable) {
        List<Order> orders = orderRepository.findOrders(email, pageable);

        List<OrderServiceResponse> responses = orders.stream().map(OrderServiceResponse::of)
                .toList();

        return PageResponse.builder()
                .pageNo(pageable.getPageNumber())
                .hasNext(orders.size() > pageable.getPageSize())
                .contents(Collections.singletonList(responses.subList(0, pageable.getPageSize())))
                .build();
    }

    public boolean validateOrder(UUID orderId, String email) {
        Member member = getMemberByEmail(email);
        Order order = getOrderById(orderId);
        Member orderMember = order.getMember();

        return Objects.equals(member.getEmail(), orderMember.getEmail());
    }

    @Transactional
    public void cancel(UUID orderId) {
        // TODO: 324
        Order order = getOrderById(orderId);
        order.cancel();
    }


    private void deductStockQuantity(Product product, int quantity) {
        Stock stock = getStockByProduct(product);

        // 재고 차감 시도
        if (stock.isQuantityLessThan(quantity)) {
            log.warn("재고가 부족한 상품이 있습니다. productId={}, stock.quantity={}", product.getId(),
                    stock.getQuantity());
            throw new IllegalArgumentException("재고가 부족한 상품이 있습니다.");
        }

        stock.deductQuantity(quantity);
    }

    private Member getMemberByEmail(String email) {
        return memberPort.findMemberByEmail(email);
    }

    private Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "상품을 찾을 수 없습니다. productId=" + productId));
    }

    private Order getOrderById(UUID orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("주문을 찾을 수 없습니다. orderId=" + orderId));
    }

    private Stock getStockByProduct(Product product) {
        return stockRepository.findByProduct(product)
                .orElseThrow(() -> new EntityNotFoundException(
                        "상품 재고를 찾을 수 없습니다. productId=" + product.getId()));
    }
}
