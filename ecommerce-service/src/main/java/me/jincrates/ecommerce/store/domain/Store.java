package me.jincrates.ecommerce.store.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.ecommerce.member.domain.Member;
import me.jincrates.ecommerce.product.domain.Product;
import me.jincrates.global.common.BaseTimeEntity;
import me.jincrates.global.common.StringListConverter;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Comment;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @Comment("매니저 ID")
    private Member member;

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
    @Comment("이미지 url 목록")
    private List<String> imageUrls = new ArrayList<>();

    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();  // 상품 list

    @Builder(access = AccessLevel.PRIVATE)
    private Store(Member member, String name, String description, String address,
        StoreStatus status, List<String> imageUrls) {
        Assert.notNull(member, "매니저 정보는 필수입니다.");
        Assert.notNull(name, "상점 이름은 필수입니다.");
        Assert.notNull(address, "상점 주소는 필수입니다.");
        Assert.notNull(status, "상점 상태는 필수입니다.");

        this.member = member;
        this.name = name;
        this.description = description;
        this.address = address;
        this.status = status;
        this.imageUrls = imageUrls;
    }

    public static Store create(Member member, String name, String description, String address,
        List<String> imageUrls) {
        return Store.builder()
            .member(member)
            .name(name)
            .description(description)
            .address(address)
            .status(StoreStatus.READY)
            .imageUrls(imageUrls)
            .build();
    }

    public void open() {
        this.status = StoreStatus.OPEN;
    }

    public void close() {
        this.status = StoreStatus.CLOSED;
    }

    public void update(String name, String description, String address,
        List<String> imageUrls) {
        if (!StringUtils.isBlank(name)) {
            setName(name);
        }
        if (!StringUtils.isBlank(description)) {
            setDescription(description);
        }
        if (!StringUtils.isBlank(address)) {
            setAddress(address);
        }
        if (!ObjectUtils.isEmpty(imageUrls)) {
            setImageUrls(imageUrls);
        }
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    private void setAddress(String address) {
        this.address = address;
    }

    private void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }
}
