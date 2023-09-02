package me.jincrates.api.orders.api.controller;

import lombok.RequiredArgsConstructor;
import me.jincrates.api.orders.api.controller.response.OrderListResponse;
import me.jincrates.api.orders.api.controller.response.OrderProductListResponse;
import me.jincrates.api.orders.api.controller.response.PageCommonResponse;
import me.jincrates.api.orders.api.service.OrderService;
import me.jincrates.api.claims.domain.orderproduct.OrderProduct;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("order")
    public PageCommonResponse<?> getOrderList() {
        Long orderId = 1L;

        List<OrderProductListResponse> orderProductList = List.of(
                OrderProductListResponse.builder()
                        .orderProductId(1L)
                        .status("배송완료")
                        .name("상품명1")
                        .option("옵션명1")
                        .price(1000)
                        .quantity(1)
                        .build(),
                OrderProductListResponse.builder()
                        .orderProductId(2L)
                        .status("배송완료")
                        .name("상품명2")
                        .option("옵션명2")
                        .price(2000)
                        .quantity(2)
                        .build()
        );

        OrderListResponse orderList = OrderListResponse.builder()
                .orderId(orderId)
                .orderedAt(LocalDateTime.now())
                .orderProductList(PageCommonResponse.of(orderProductList))
                .build();
        return PageCommonResponse.of(List.of(orderList));
    }

    @GetMapping("order/product")
    public PageCommonResponse<?> getOrderProductList(
            Pageable pageable
    ) {
        Long orderId = 1L;

        List<OrderProductListResponse> orderProductList = List.of(
                OrderProductListResponse.builder()
                        .orderProductId(1L)
                        .status("배송완료")
                        .name("상품명1")
                        .option("옵션명1")
                        .price(1000)
                        .quantity(1)
                        .build(),
                OrderProductListResponse.builder()
                        .orderProductId(2L)
                        .status("배송완료")
                        .name("상품명2")
                        .option("옵션명2")
                        .price(2000)
                        .quantity(2)
                        .build()
        );

        List<OrderProduct> orderProducts = orderService.findAllOrderProductByOrderId(orderId, 1L);

        return PageCommonResponse.of(List.of(orderProductList));
    }
}
