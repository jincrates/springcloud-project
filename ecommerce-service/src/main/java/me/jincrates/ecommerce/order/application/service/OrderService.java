package me.jincrates.ecommerce.order.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jincrates.ecommerce.cart.application.port.CartPort;
import me.jincrates.ecommerce.member.application.port.MemberPort;
import me.jincrates.ecommerce.member.domain.Member;
import me.jincrates.ecommerce.order.application.port.OrderPort;
import me.jincrates.ecommerce.order.application.port.OrderUseCase;
import me.jincrates.ecommerce.order.application.service.request.OrderCreateRequest;
import me.jincrates.ecommerce.order.application.service.response.OrderResponse;
import me.jincrates.ecommerce.order.domain.Order;
import me.jincrates.ecommerce.order.domain.OrderItem;
import me.jincrates.ecommerce.product.application.port.ProductPort;
import me.jincrates.ecommerce.product.application.port.StockPort;
import me.jincrates.ecommerce.product.domain.Product;
import me.jincrates.ecommerce.product.domain.Stock;
import me.jincrates.infra.retry.application.RetryPort;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService implements OrderUseCase {

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

        List<OrderItem> orderItems = request.orderProducts().stream()
                .map(op -> {
                    Product product = productPort.findProductById(op.productId());
                    // 재고 차감 - 재시도 3번
                    retryPort.executeWithRetry(() -> {
                        deductStockQuantity(product, op.quantity());
                        return Void.TYPE;
                    });

                    return OrderItem.create(product, op.quantity());
                }).toList();

        Order order = orderPort.saveOrder(Order.create(member, orderItems));

        // 장바구니 삭제
        List<Long> productIds = orderItems.stream().map(oi -> oi.getProduct().getId()).toList();
        cartPort.deleteCartItemByMemberIdAndProductIdIn(memberId, productIds);

        return OrderResponse.of(order);
    }

    @Override
    @Transactional
    public OrderResponse cancelOrder(Long orderId, Long memberId) {
        Order order = orderPort.findOrderByIdAndMemberId(orderId, memberId);
        // TODO: 부분취소 고려필요
        order.cancel("취소 사유입니다.");
        return OrderResponse.of(order);
    }

    @Override
    public List<OrderResponse> getOrders(Long memberId, Pageable pageable) {
        List<Order> orders = orderPort.findAllOrderByMemberId(memberId, pageable);
        return orders.stream()
                .map(OrderResponse::of)
                .toList();
    }

    @Override
    public OrderResponse getOrder(Long orderId, Long memberId) {
        Order order = orderPort.findOrderByIdAndMemberId(orderId, memberId);
        return OrderResponse.of(order);
    }

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
