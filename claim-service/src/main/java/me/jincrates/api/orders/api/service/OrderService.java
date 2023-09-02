package me.jincrates.api.orders.api.service;

import me.jincrates.api.claims.domain.orderproduct.OrderProduct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    public List<OrderProduct> findAllOrderProductByOrderId(Long orderId, Long userId) {
        return null;
    }
}
