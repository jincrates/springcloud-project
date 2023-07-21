package me.jincrates.msa.coffeekiosk.spring.api.controller.order;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import me.jincrates.msa.coffeekiosk.spring.api.controller.order.request.OrderCreateRequest;
import me.jincrates.msa.coffeekiosk.spring.api.service.order.OrderService;
import me.jincrates.msa.coffeekiosk.spring.api.service.order.response.OrderResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/api/v1/orders/new")
    public OrderResponse createOrder(@RequestBody OrderCreateRequest request) {
        LocalDateTime registeredAt = LocalDateTime.now();
        return orderService.createOrder(request, registeredAt);
    }
}
