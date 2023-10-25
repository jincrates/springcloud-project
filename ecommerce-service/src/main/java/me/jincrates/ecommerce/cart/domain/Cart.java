package me.jincrates.ecommerce.cart.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.ecommerce.member.domain.Member;
import me.jincrates.ecommerce.product.domain.Product;
import me.jincrates.global.common.BaseEntity;
import org.hibernate.annotations.Comment;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Comment("장바구니")
@Table(name = "carts")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    @Comment("장바구니 ID")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @Comment("회원 ID")
    private Member member;

    @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();  // 장바구니 상품 list

    public Cart(Member member) {
        this.member = member;
    }

    @Builder(access = AccessLevel.PRIVATE)
    private Cart(Member member, List<CartItem> cartItems) {
        Assert.notNull(member, "주문자는 필수입니다.");
        Assert.notNull(cartItems, "장바구니 항목은 필수입니다.");
        Assert.isTrue(cartItems.size() == 0, "장바구니 항목은 필수입니다.");

        this.member = member;
        this.cartItems = cartItems;
    }

    public static Cart create(Member member, List<CartItem> cartItems) {
        return Cart.builder()
                .member(member)
                .cartItems(cartItems)
                .build();
    }

    public void addCartItem(Product product, int quantity) {
        cartItems.add(CartItem.create(this, product, quantity));
    }
}
