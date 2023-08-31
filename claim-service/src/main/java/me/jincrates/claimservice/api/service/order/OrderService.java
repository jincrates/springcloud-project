package me.jincrates.claimservice.api.service.order;

import java.util.List;
import me.jincrates.claimservice.domain.orderproduct.OrderProduct;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    public List<OrderProduct> findAllOrderProductByOrderId(Long orderId, Long userId) {
        return null;
    }
}
