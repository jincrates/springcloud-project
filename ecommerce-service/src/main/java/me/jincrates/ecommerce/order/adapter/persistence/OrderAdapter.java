package me.jincrates.ecommerce.order.adapter.persistence;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import me.jincrates.ecommerce.order.application.port.OrderPort;
import me.jincrates.ecommerce.order.domain.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
class OrderAdapter implements OrderPort {

    private final OrderRepository orderRepository;

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order findOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("주문을 찾을 수 없습니다. orderId=" + orderId));
    }

    @Override
    public List<Order> findAllOrderByMemberId(Long memberId, Pageable pageable) {
        return orderRepository.findAllByMemberId(memberId, pageable).getContent();
    }

    @Override
    public Order findOrderByIdAndMemberId(Long orderId, Long memberId) {
        return orderRepository.findByIdAndMemberId(orderId, memberId)
                .orElseThrow(() -> new EntityNotFoundException("주문을 찾을 수 없습니다. orderId=" + orderId + ", memberId=" + memberId));
    }
}
