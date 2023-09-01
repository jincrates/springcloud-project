package me.jincrates.api.boilerplate.api.controller.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.api.boilerplate.api.service.request.BoilerCreateServiceRequest;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoilerCreateRequest {

    @NotEmpty(message = "number는 필수입니다.")
    private Integer number;

    public BoilerCreateRequest(Integer number) {
        this.number = number;
    }

    public BoilerCreateServiceRequest toServiceRequest() {
        return new BoilerCreateServiceRequest(this.number);
    }
}
