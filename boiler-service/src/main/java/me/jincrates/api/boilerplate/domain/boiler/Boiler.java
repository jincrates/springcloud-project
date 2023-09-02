package me.jincrates.api.boilerplate.domain.boiler;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.api.boilerplate.api.service.request.BoilerUpdateServiceRequest;
import me.jincrates.api.global.common.BaseEntity;

@Getter
@jakarta.persistence.Entity
@Table(name = "BOILER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Boiler extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BoilerStatus status;

    @Column(nullable = false)
    private Integer number;

    private Boiler(BoilerStatus status, Integer number) {
        this.status = status;
        this.number = number;
    }

    @Builder(access = AccessLevel.PRIVATE)
    private Boiler(Long id, BoilerStatus status, Integer number) {
        this.id = id;
        this.status = status;
        this.number = number;
    }

    public static Boiler create(Integer number) {
        return new Boiler(BoilerStatus.NOT_STARTED, number);
    }

    public void canceled() {
        this.status = BoilerStatus.NOT_STARTED;
    }

    public void progress() {
        this.status = BoilerStatus.IN_PROGRESS;
    }

    public void completed() {
        this.status = BoilerStatus.COMPLETED;
    }

    public Boiler update(BoilerUpdateServiceRequest request) {
        return Boiler.builder()
                .id(request.getId())
                .status(request.getStatus())
                .number(request.getNumber())
                .build();
    }
}
