package me.jincrates.api.boilerplate.api.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.api.boilerplate.api.service.request.BoilerCreateServiceRequest;
import me.jincrates.api.boilerplate.domain.boiler.BoilerStatus;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "Boiler 등록 요청 DTO")
public class BoilerCreateRequest {

    @Schema(description = "보일러 상태")
    private BoilerStatus status;

    @Schema(description = "NUMBER")
    @Positive(message = "number은 0보다 커야합니다.")
    private Integer number;

    public BoilerCreateRequest(Integer number) {
        this.number = number;
    }

    public BoilerCreateRequest(BoilerStatus status, Integer number) {
        this.status = status;
        this.number = number;
    }

    public BoilerCreateServiceRequest toServiceRequest() {
        return new BoilerCreateServiceRequest(this.number);
    }
}
