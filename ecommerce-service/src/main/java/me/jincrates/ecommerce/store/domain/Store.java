package me.jincrates.ecommerce.store.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.ecommerce.product.domain.Product;
import me.jincrates.global.common.BaseTimeEntity;
import me.jincrates.global.common.StringListConverter;
import org.hibernate.annotations.Comment;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Comment("상점")
@Table(name = "stores")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    @Comment("상점 ID")
    private Long id;

    @Column(name = "member_id", nullable = false)
    @Comment("관리자 ID")
    private Long memberId;

    @Column(nullable = false, length = 50)
    @Comment("상점 이름")
    private String name;

    @Column(length = 500)
    @Comment("상점 설명")
    private String description;

    @Column(nullable = false, length = 50)
    @Comment("상점 주소")
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Comment("상점 상태")
    private StoreStatus status;

    @Column(name = "image_urls", columnDefinition = "longtext")
    @Convert(converter = StringListConverter.class)
    @Comment("상점 이미지")
    private List<String> imageUrls = new ArrayList<>();

    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();  // 상품 list

    @Builder(access = AccessLevel.PRIVATE)
    private Store(Long memberId, String name, String description, String address, StoreStatus status, List<String> imageUrls) {
        Assert.notNull(memberId, "회원 ID는 필수입니다.");
        Assert.notNull(name, "상점 이름은 필수입니다.");
        Assert.notNull(address, "상점 주소는 필수입니다.");
        Assert.notNull(status, "상점 상태는 필수입니다.");

        this.memberId = memberId;
        this.name = name;
        this.description = description;
        this.address = address;
        this.status = status;
        this.imageUrls = imageUrls;
    }
}
