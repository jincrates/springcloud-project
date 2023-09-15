package me.jincrates.ecommerce.carts.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.ecommerce.members.domain.Member;
import me.jincrates.global.common.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "CART")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartProduct> cartProducts = new ArrayList<>();  // 장바구니 상품 list


    @Builder(access = AccessLevel.PRIVATE)
    private Cart(Member member, List<CartProduct> cartProducts) {
        this.member = member;
        this.cartProducts = cartProducts;
    }

    public static Cart create(Member member, List<CartProduct> cartProducts) {
        return Cart.builder()
                .member(member)
                .cartProducts(cartProducts)
                .build();
    }
}
