package me.jincrates.ecommerce.cart.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.ecommerce.product.domain.Product;
import me.jincrates.global.common.BaseEntity;

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

    public CartProduct(Cart cart, Product product, int quantity) {
        this.cart = cart;
        this.product = product;
        this.quantity = quantity;
    }

    public static CartProduct create(Cart cart, Product product, int quantity) {
        return new CartProduct(cart, product, quantity);
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    public void updateQuantity(int quantity) {
        this.quantity = quantity;
    }
}
