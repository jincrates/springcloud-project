package me.jincrates.msa.coffeekiosk.spring.temp.api.service.order.response;

import lombok.Builder;
import lombok.Getter;
import me.jincrates.msa.coffeekiosk.spring.temp.api.service.product.response.ProductResponse;
import me.jincrates.msa.coffeekiosk.spring.temp.domain.order.Order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class OrderResponse {

    private Long id;
    private int totalPrice;
    private LocalDateTime registeredAt;
    private List<ProductResponse> products = new ArrayList<>();

    @Builder
    private OrderResponse(Long id, int totalPrice, LocalDateTime registeredAt,
                          List<ProductResponse> products) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.registeredAt = registeredAt;
        this.products = products;
    }

    public static OrderResponse of(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .totalPrice(order.getTotalPrice())
                .registeredAt(order.getRegisteredAt())
                .products(order.getOrderProducts().stream()
                        .map(orderProduct -> ProductResponse.of(orderProduct.getProduct()))
                        .toList()
                )
                .build();
    }
}
