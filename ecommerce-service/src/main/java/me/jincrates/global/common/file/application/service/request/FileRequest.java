package me.jincrates.global.common.file.application.service.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

@Schema(description = "파일 업로드 request")
public record FileRequest(

        @Schema(description = "업로드 파일")
        @NotNull(message = "업로드할 파일이 없습니다.")
        MultipartFile file
) {
}
