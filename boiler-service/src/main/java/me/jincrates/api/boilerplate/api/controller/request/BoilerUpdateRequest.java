package me.jincrates.api.boilerplate.api.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.api.boilerplate.api.service.request.BoilerUpdateServiceRequest;
import me.jincrates.api.boilerplate.domain.entity.BoilerStatus;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "Boiler 수정 요청 DTO")
public class BoilerUpdateRequest {

    @Schema(description = "ID")
    @Positive(message = "number은 0보다 커야합니다.")
    private Long id;

    @Schema(description = "상태")
    private BoilerStatus status;

    @Schema(description = "NUMBER")
    @Positive(message = "number은 0보다 커야합니다.")
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
