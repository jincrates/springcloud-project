package me.jincrates.claimservice.domain.product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.claimservice.domain.BaseEntity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "PRODUCT")
@Entity
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int price;

    private int stock;

    @Builder
    private Product(String name, int price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
}
