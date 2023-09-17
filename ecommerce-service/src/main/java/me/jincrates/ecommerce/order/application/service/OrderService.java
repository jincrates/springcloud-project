package me.jincrates.ecommerce.order.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jincrates.ecommerce.cart.application.port.CartPort;
import me.jincrates.ecommerce.member.application.port.MemberPort;
import me.jincrates.ecommerce.member.domain.Member;
import me.jincrates.ecommerce.order.application.port.OrderCancelUseCase;
import me.jincrates.ecommerce.order.application.port.OrderCreateUseCase;
import me.jincrates.ecommerce.order.application.port.OrderPort;
import me.jincrates.ecommerce.order.application.service.request.OrderCancelRequest;
import me.jincrates.ecommerce.order.application.service.request.OrderCreateRequest;
import me.jincrates.ecommerce.order.application.service.response.OrderResponse;
import me.jincrates.ecommerce.order.domain.Order;
import me.jincrates.ecommerce.order.domain.OrderProduct;
import me.jincrates.ecommerce.product.application.port.ProductPort;
import me.jincrates.ecommerce.product.application.port.StockPort;
import me.jincrates.ecommerce.product.domain.Product;
import me.jincrates.ecommerce.product.domain.Stock;
import me.jincrates.ecommerce.retry.application.RetryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

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
    private final RetryPort retryPort;

    @Override
    @Transactional
    public OrderResponse createOrder(OrderCreateRequest request, Long memberId) {
        Member member = memberPort.findMemberById(memberId);

        List<OrderProduct> orderProducts = request.orderProducts().stream()
                .map(op -> {
                    Product product = productPort.findProductById(op.productId());
                    // 재고 차감 - 재시도 3번
                    retryPort.executeWithRetry(() -> {
                        deductStockQuantity(product, op.quantity());
                        return Void.TYPE;
                    });

                    return OrderProduct.create(product, op.quantity());
                }).toList();

        Order order = orderPort.saveOrder(Order.create(member, orderProducts));

        // 결제로직
//        order.progress();
//        order.success();

        return OrderResponse.of(order);
    }

    @Override
    @Transactional
    public OrderResponse cancelOrder(OrderCancelRequest request, Long memberId) {
        Member member = memberPort.findMemberById(memberId);
        Order order = orderPort.findOrderById(request.orderId());

        if (!Objects.equals(member.getId(), order.getMember().getId())) {
            throw new IllegalArgumentException("주문자 정보와 일치하지 않습니다. order.memberId" + order.getMember().getId());
        }

        // TODO: 부분취소 고려필요
        order.cancel();

        return OrderResponse.of(order);
    }

//    @Transactional
//    public OrderResponse order(OrderCreateRequest request, String email) {
//        Member member = memberPort.findMemberByEmail(email);
//
//        Product product = productPort.findProductById(request.);
//
//        // 재고 차감
//        deductStockQuantity(product, request.getQuantity());
//
//        List<OrderProduct> orderProducts = new ArrayList<>();
//        OrderProduct orderProduct = OrderProduct.create(product, request.getQuantity());
//        orderProducts.add(orderProduct);
//
//        Order order = Order.create(member, orderProducts);
//        orderPort.saveOrder(order);
//
//        // 결제로직
//        order.progress();
//        order.success();
//
//        return OrderResponse.of(order);
//    }
//
//    @Transactional
//    public OrderResponse orders(List<OrderCreateServiceRequest> requests, String email) {
//        Member member = memberPort.findMemberByEmail(email);
//
//        List<OrderProduct> orderProducts = new ArrayList<>();
//        for (OrderCreateServiceRequest request : requests) {
//            Product product = productPort.findProductById(request.getProductId());
//            OrderProduct orderProduct = OrderProduct.create(product, request.getQuantity());
//            orderProducts.add(orderProduct);
//        }
//
//        Order order = Order.create(member, orderProducts);
//        orderPort.saveOrder(order);
//
//        // 결제로직
//        order.progress();
//        order.success();
//
//        return OrderResponse.of(order);
//    }

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

    private void deductStockQuantity(Product product, Integer quantity) {
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
