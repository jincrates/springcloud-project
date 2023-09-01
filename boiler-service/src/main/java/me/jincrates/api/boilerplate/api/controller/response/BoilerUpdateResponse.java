package me.jincrates.api.boilerplate.api.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.api.boilerplate.domain.entity.BoilerStatus;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "수정된 Boiler DTO")
public class BoilerUpdateResponse {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "상태")
    private BoilerStatus status;

    @Schema(description = "NUMBER")
    private Integer number;

    @Builder(access = AccessLevel.PRIVATE)
    private BoilerUpdateResponse(Long id, BoilerStatus status, Integer number) {
        this.id = id;
        this.status = status;
        this.number = number;
    }

    public static BoilerUpdateResponse of(Long id, BoilerStatus status, Integer number) {
        return BoilerUpdateResponse.builder()
                .id(id)
                .status(status)
                .number(number)
                .build();
    }
}
