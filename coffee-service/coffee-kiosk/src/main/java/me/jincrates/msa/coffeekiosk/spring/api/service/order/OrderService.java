package me.jincrates.msa.coffeekiosk.spring.api.service.order;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import me.jincrates.msa.coffeekiosk.spring.api.controller.order.request.OrderCreateRequest;
import me.jincrates.msa.coffeekiosk.spring.api.service.order.response.OrderResponse;
import me.jincrates.msa.coffeekiosk.spring.domain.order.Order;
import me.jincrates.msa.coffeekiosk.spring.domain.order.OrderRepository;
import me.jincrates.msa.coffeekiosk.spring.domain.product.Product;
import me.jincrates.msa.coffeekiosk.spring.domain.product.ProductRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public OrderResponse createOrder(OrderCreateRequest request, LocalDateTime registeredAt) {
        List<String> productNumbers = request.getProductNumbers();
        List<Product> products = findProductsBy(productNumbers);

        Order order = Order.create(products, registeredAt);
        Order savedOrder = orderRepository.save(order);

        return OrderResponse.of(savedOrder);
    }

    private List<Product> findProductsBy(List<String> productNumbers) {
        // 중복이 제거된 products
        List<Product> products = productRepository.findAllByProductNumberIn(
            productNumbers);
        Map<String, Product> productMap = products.stream()
            .collect(Collectors.toMap(Product::getProductNumber, p -> p));

        // 중복을 포함하는 결과
        return productNumbers.stream()
            .map(productMap::get)
            .toList();
    }
}
