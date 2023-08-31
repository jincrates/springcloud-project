package me.jincrates.msa.coffeekiosk.spring.temp.api.controller.order;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.jincrates.msa.coffeekiosk.spring.global.common.response.CommonResponse;
import me.jincrates.msa.coffeekiosk.spring.temp.api.controller.order.request.OrderCreateRequest;
import me.jincrates.msa.coffeekiosk.spring.temp.api.service.order.OrderService;
import me.jincrates.msa.coffeekiosk.spring.temp.api.service.order.response.OrderResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/api/v1/orders/new")
    public CommonResponse<OrderResponse> createOrder(@Valid @RequestBody OrderCreateRequest request) {
        LocalDateTime registeredAt = LocalDateTime.now();
        return CommonResponse.ok(orderService.createOrder(request.toServiceRequest(), registeredAt));
    }
}
