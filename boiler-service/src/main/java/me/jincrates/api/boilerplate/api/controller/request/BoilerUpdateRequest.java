package me.jincrates.api.boilerplate.api.controller.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.api.boilerplate.api.service.request.BoilerUpdateServiceRequest;
import me.jincrates.api.boilerplate.domain.entity.BoilerStatus;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoilerUpdateRequest {

    @NotEmpty(message = "id는 필수입니다.")
    private Long id;
    private BoilerStatus status;
    @NotEmpty(message = "number는 필수입니다.")
    private Integer number;

    public BoilerUpdateRequest(Long id, BoilerStatus status, Integer number) {
        this.id = id;
        this.status = status;
        this.number = number;
    }

    public BoilerUpdateServiceRequest toServiceRequest() {
        return new BoilerUpdateServiceRequest(id, status, number);
    }
}
