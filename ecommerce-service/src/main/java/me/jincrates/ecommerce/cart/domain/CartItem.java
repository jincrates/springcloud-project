package me.jincrates.ecommerce.cart.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.ecommerce.product.domain.Product;
import me.jincrates.global.common.BaseTimeEntity;
import org.hibernate.annotations.Comment;

@Getter
@Entity
@Comment("장바구니 항목")
@Table(name = "cart_items")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartItem extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    @Comment("장바구니 항목 ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    @Comment("장바구니 ID")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @Comment("상품 ID")
    private Product product;

    @Column(nullable = false)
    @Comment("장바구니 수량")
    private Integer quantity;  // 담은 수량

    @Builder(access = AccessLevel.PRIVATE)
    private CartItem(Cart cart, Product product, Integer quantity) {
        this.cart = cart;
        this.product = product;
        this.quantity = quantity;
    }

    public static CartItem create(Cart cart, Product product, Integer quantity) {
        return new CartItem(cart, product, quantity);
    }

    public void addQuantity(Integer quantity) {
        this.quantity += quantity;
    }

    public void updateQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
