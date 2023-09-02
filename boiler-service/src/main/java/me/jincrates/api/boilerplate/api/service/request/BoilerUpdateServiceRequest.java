package me.jincrates.api.boilerplate.api.service.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.api.boilerplate.domain.boiler.BoilerStatus;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoilerUpdateServiceRequest {

    private Long id;
    private BoilerStatus status;
    private Integer number;

    public BoilerUpdateServiceRequest(Long id, BoilerStatus status, Integer number) {
        this.id = id;
        this.status = status;
        this.number = number;
    }
}
