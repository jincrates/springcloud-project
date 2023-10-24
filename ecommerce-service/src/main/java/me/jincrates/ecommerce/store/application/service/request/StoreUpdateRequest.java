package me.jincrates.ecommerce.store.application.service.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import org.hibernate.validator.constraints.Length;

public record StoreUpdateRequest(
    @Schema(description = "상점 이름", example = "커피와 삶")
    @Length(max = 50, message = "상점 이름은 50자까지만 입력 가능합니다.")
    @NotBlank(message = "상점 이름은 필수입니다.")
    String name,

    @Schema(description = "상점 설명", example = "맛있는 커피를 내려드립니다.")
    @Length(max = 500, message = "상점 설명은 500자까지만 입력 가능합니다.")
    String description,

    @Schema(description = "상점 주소", example = "서울 화곡동")
    @Length(max = 50, message = "상점 주소는 50자까지만 입력 가능합니다.")
    @NotBlank(message = "상점 주소는 필수입니다.")
    String address,

    @Schema(description = "업로드 이미지 url 목록")
    List<String> imageUrls
) {

}
