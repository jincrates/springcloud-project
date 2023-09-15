package me.jincrates.ecommerce.order.adapter.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import me.jincrates.ecommerce.order.application.port.OrderUseCase;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "주문 서비스", description = "주문 등록/조회 API")
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderUseCase orderUseCase;
}
