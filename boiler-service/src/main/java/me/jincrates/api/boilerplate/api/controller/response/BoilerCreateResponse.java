package me.jincrates.api.boilerplate.api.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.api.boilerplate.domain.boiler.BoilerStatus;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "등록된 Boiler DTO")
public class BoilerCreateResponse {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "상태")
    private BoilerStatus status;

    @Schema(description = "NUMBER")
    private Integer number;

    @Builder(access = AccessLevel.PRIVATE)
    private BoilerCreateResponse(Long id, BoilerStatus status, Integer number) {
        this.id = id;
        this.status = status;
        this.number = number;
    }

    public static BoilerCreateResponse of(Long id, BoilerStatus status, Integer number) {
        return BoilerCreateResponse.builder()
                .id(id)
                .status(status)
                .number(number)
                .build();
    }
}
