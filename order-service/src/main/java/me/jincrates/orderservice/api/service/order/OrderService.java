package me.jincrates.orderservice.api.service.order;

import me.jincrates.orderservice.domain.orderproduct.OrderProduct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    public List<OrderProduct> findAllOrderProductByOrderId(Long orderId, Long userId) {
        return null;
    }
}
