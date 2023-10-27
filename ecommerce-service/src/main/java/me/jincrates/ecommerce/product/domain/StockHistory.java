package me.jincrates.ecommerce.product.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

@Getter
@Entity
@Comment("재고 내역")
@Table(name = "stock_history")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class StockHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_history_id")
    @Comment("재고 내역 ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id")
    @Comment("재고 ID")
    private Stock stock;

    @Column(nullable = false)
    @Comment("재고 수량")
    private Integer quantity;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Comment("타입(IN: 입고, OUT: 출고)")
    private StockType type;

    @Version
    private int version;

    @CreatedDate
    @Column(name = "created_at", updatable = false, nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @Comment("생성일시")
    private LocalDateTime createdAt;

    @Builder(access = AccessLevel.PRIVATE)
    private StockHistory(Stock stock, Integer quantity, StockType type) {
        Assert.notNull(stock, "재고 정보는 필수입니다.");
        Assert.notNull(quantity, "재고 내역 수량은 필수입니다.");
        Assert.isTrue(quantity >= 0, "재고 내역 수량은 0 이상이여야 합니다.");
        Assert.notNull(type, "재고 타입은 필수입니다.");

        this.stock = stock;
        this.quantity = quantity;
        this.type = type;
    }

    public static StockHistory create(Stock stock, Integer quantity, StockType type) {
        return StockHistory.builder()
                .stock(stock)
                .quantity(quantity)
                .type(type)
                .build();
    }
}
