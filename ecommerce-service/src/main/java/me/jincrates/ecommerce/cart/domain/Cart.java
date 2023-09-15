package me.jincrates.ecommerce.cart.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.ecommerce.member.domain.Member;
import me.jincrates.global.common.BaseEntity;

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
