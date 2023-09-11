package me.jincrates.api.ecommerce.api.service;

import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jincrates.api.ecommerce.api.service.request.CartProductOrderServiceRequest;
import me.jincrates.api.ecommerce.api.service.request.OrderCreateServiceRequest;
import me.jincrates.api.ecommerce.api.service.response.OrderListPageServiceResponse;
import me.jincrates.api.ecommerce.api.service.response.OrderServiceResponse;
import me.jincrates.api.ecommerce.domain.cart.CartProduct;
import me.jincrates.api.ecommerce.domain.cart.CartProductRepository;
import me.jincrates.api.ecommerce.domain.cart.CartRepository;
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

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final StockRepository stockRepository;
    private final CartRepository cartRepository;
    private final CartProductRepository cartProductRepository;

    @Transactional
    public OrderServiceResponse order(OrderCreateServiceRequest request, String email) {
        Member member = getMemberByEmail(email);

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
        Member member = getMemberByEmail(email);

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
    public OrderServiceResponse orderCartProducts(List<CartProductOrderServiceRequest> requests,
        String email) {
        Member member = getMemberByEmail(email);

        // TODO: 메소드 추출
        List<OrderProduct> orderProducts = new ArrayList<>();

        for (CartProductOrderServiceRequest request : requests) {
            CartProduct cartProduct = getCartProductById(request.getCartProductId());

            OrderProduct orderProduct = OrderProduct.create(cartProduct.getProduct(),
                cartProduct.getQuantity());
            orderProducts.add(orderProduct);
        }

        Order order = Order.create(member, orderProducts);
        orderRepository.save(order);

        // 결제로직
        order.progress();
        order.success();

        // 장바구니 제거
        // TODO: 각 서비스로 역할 분리
        for (CartProductOrderServiceRequest request : requests) {
            CartProduct cartProduct = getCartProductById(request.getCartProductId());

            cartProductRepository.delete(cartProduct);
        }
        return null;
    }

    public OrderListPageServiceResponse getOrderListPage(String email, Pageable pageable) {
        Page<Order> orders = orderRepository.findOrdersPage(email, pageable);

        List<OrderServiceResponse> responses = orders.stream().map(OrderServiceResponse::of)
            .toList();

        return OrderListPageServiceResponse.builder()
            .pageNo(orders.getPageable().getPageNumber())
            .hasNext(orders.hasNext())
            .contents(responses)
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
        Stock stock = stockRepository.findByProduct(product);

        // 재고 차감 시도
        if (stock.isQuantityLessThan(quantity)) {
            log.warn("재고가 부족한 상품이 있습니다. productId={}, stock.quantity={}", product.getId(),
                stock.getQuantity());
            throw new IllegalArgumentException("재고가 부족한 상품이 있습니다.");
        }

        stock.deductQuantity(quantity);
    }

    private Member getMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
            .orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다. email=" + email));
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

    private CartProduct getCartProductById(Long cartProductId) {
        return cartProductRepository.findById(cartProductId)
            .orElseThrow(() -> new EntityNotFoundException(
                "장바구니 상품을 찾을 수 없습니다. cartProductId=" + cartProductId));
    }
}
