package me.jincrates.ecommerce.order.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jincrates.ecommerce.cart.application.port.CartPort;
import me.jincrates.ecommerce.member.application.port.MemberPort;
import me.jincrates.ecommerce.member.domain.Member;
import me.jincrates.ecommerce.order.application.port.OrderCancelUseCase;
import me.jincrates.ecommerce.order.application.port.OrderCreateUseCase;
import me.jincrates.ecommerce.order.application.port.OrderPort;
import me.jincrates.ecommerce.order.application.service.request.OrderCancelServiceRequest;
import me.jincrates.ecommerce.order.application.service.request.OrderCreateServiceRequest;
import me.jincrates.ecommerce.order.application.service.response.OrderServiceResponse;
import me.jincrates.ecommerce.order.domain.Order;
import me.jincrates.ecommerce.order.domain.OrderProduct;
import me.jincrates.ecommerce.product.application.port.ProductPort;
import me.jincrates.ecommerce.product.application.port.StockPort;
import me.jincrates.ecommerce.product.domain.Product;
import me.jincrates.ecommerce.product.domain.Stock;
import me.jincrates.global.common.response.PageResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService implements OrderCreateUseCase, OrderCancelUseCase {

    // QueryPort, CommandPort

    private final MemberPort memberPort;
    private final ProductPort productPort;
    private final OrderPort orderPort;
    private final StockPort stockPort;
    private final CartPort cartPort;


    @Override
    public OrderServiceResponse createOrder(OrderCreateServiceRequest request) {
        return null;
    }

    @Override
    public OrderServiceResponse cancelOrder(OrderCancelServiceRequest request) {
        return null;
    }

    @Transactional
    public OrderServiceResponse order(OrderCreateServiceRequest request, String email) {
        Member member = memberPort.findMemberByEmail(email);

        Product product = productPort.findProductById(request.getProductId());

        // 재고 차감
        deductStockQuantity(product, request.getQuantity());

        List<OrderProduct> orderProducts = new ArrayList<>();
        OrderProduct orderProduct = OrderProduct.create(product, request.getQuantity());
        orderProducts.add(orderProduct);

        Order order = Order.create(member, orderProducts);
        orderPort.saveOrder(order);

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
            Product product = productPort.findProductById(request.getProductId());
            OrderProduct orderProduct = OrderProduct.create(product, request.getQuantity());
            orderProducts.add(orderProduct);
        }

        Order order = Order.create(member, orderProducts);
        orderPort.saveOrder(order);

        // 결제로직
        order.progress();
        order.success();

        return OrderServiceResponse.of(order);
    }

//    @Transactional
//    public OrderServiceResponse orderFromCart(String email) {
//        Member member = memberPort.findMemberByEmail(email);
//
//        Cart cart = cartPort.findCartByMemberId(member.getId());
//
//        List<OrderProduct> orderProducts = new ArrayList<>();
//
//        for (CartProduct cartProduct : cart.getCartProducts()) {
//            OrderProduct orderProduct = OrderProduct.create(cartProduct.getProduct(),
//                cartProduct.getQuantity());
//            orderProducts.add(orderProduct);
//
//            // 재고감소
//            Stock stock = stockPort.findStockByProduct(cartProduct.getProduct());
//            stock.deductQuantity(cartProduct.getQuantity());
//        }
//
//        Order order = Order.create(member, orderProducts);
//        orderPort.saveOrder(order);
//
//        // 결제로직
//        order.progress();
//        order.success();
//
//        // 장바구니 제거
//        cartPort.deleteAllCartProduct(cart.getCartProducts());
//
//        return OrderServiceResponse.of(order);
//    }

    public PageResponse<?> getOrders(String email, Pageable pageable) {
        List<Order> orders = orderPort.findOrders(email, pageable);

        List<OrderServiceResponse> responses = orders.stream().map(OrderServiceResponse::of)
                .toList();

        return PageResponse.builder()
                .pageNo(pageable.getPageNumber())
                .hasNext(orders.size() > pageable.getPageSize())
                .contents(Collections.singletonList(responses.subList(0, pageable.getPageSize())))
                .build();
    }

    public boolean validateOrder(UUID orderId, String email) {
        Member member = memberPort.findMemberByEmail(email);
        Order order = orderPort.findOrderById(orderId);
        Member orderMember = order.getMember();

        return Objects.equals(member.getEmail(), orderMember.getEmail());
    }

    @Transactional
    public void cancel(UUID orderId) {
        // TODO: 324
        Order order = orderPort.findOrderById(orderId);
        order.cancel();
    }


    private void deductStockQuantity(Product product, int quantity) {
        Stock stock = stockPort.findStockByProduct(product);

        // 재고 차감 시도
        if (stock.isQuantityLessThan(quantity)) {
            log.warn("재고가 부족한 상품이 있습니다. productId={}, stock.quantity={}", product.getId(),
                    stock.getQuantity());
            throw new IllegalArgumentException("재고가 부족한 상품이 있습니다.");
        }

        stock.deductQuantity(quantity);
    }
}
