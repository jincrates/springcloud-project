package me.jincrates.api.boilerplate.api.service.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoilerCreateServiceRequest {

    private Integer number;

    public BoilerCreateServiceRequest(Integer number) {
        this.number = number;
    }
}
