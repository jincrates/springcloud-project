package me.jincrates.api.ecommerce.domain.cart;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.api.ecommerce.domain.member.Member;
import me.jincrates.api.global.common.BaseEntity;

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

    @Builder(access = AccessLevel.PRIVATE)
    private Cart(Member member) {
        this.member = member;
    }

    public static Cart create(Member member) {
        return Cart.builder()
                .member(member)
                .build();
    }
}
