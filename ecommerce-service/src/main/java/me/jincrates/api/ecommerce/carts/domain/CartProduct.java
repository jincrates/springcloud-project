package me.jincrates.api.ecommerce.carts.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.api.ecommerce.products.domain.Product;
import me.jincrates.api.global.common.BaseEntity;

@Getter
@Entity
@Table(name = "CART_PRODUCT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartProduct extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_product_id")
    private Long id;  // 장바구니 상품 ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;  // 담은 수량

    @Builder(access = AccessLevel.PRIVATE)
    private CartProduct(Cart cart, Product product, int quantity) {
        this.cart = cart;
        this.product = product;
        this.quantity = quantity;
    }

    public static CartProduct create(Cart cart, Product product, int quantity) {
        return CartProduct.builder()
                .cart(cart)
                .product(product)
                .quantity(quantity)
                .build();
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    public void updateQuantity(int quantity) {
        this.quantity = quantity;
    }
}
