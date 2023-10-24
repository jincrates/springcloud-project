package me.jincrates.ecommerce.store.application.service.response;

import io.swagger.v3.oas.annotations.media.Schema;
import me.jincrates.ecommerce.store.domain.Store;
import me.jincrates.ecommerce.store.domain.StoreStatus;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "상점 response")
public record StoreResponse(
        @Schema(description = "상점 ID")
        Long storeId,

        @Schema(description = "상점 이름")
        String name,

        @Schema(description = "상점 설명")
        String description,

        @Schema(description = "상점 주소")
        String address,

        @Schema(description = "상점 상태")
        StoreStatus status,

        @Schema(description = "이미지 url 목록")
        List<String> imageUrls,

//    @Schema(description = "상품 목록")
//    List<ProductResponse> products,

        @Schema(description = "생성일시")
        LocalDateTime createdAt,

        @Schema(description = "수정일시")
        LocalDateTime updatedAt,

        @Schema(description = "매니저 ID")
        Long memberId,

        @Schema(description = "매니저 이름")
        String memberName
) {

    public static StoreResponse of(Store store) {
        return new StoreResponse(
                store.getId(),
                store.getName(),
                store.getDescription(),
                store.getAddress(),
                store.getStatus(),
                store.getImageUrls(),
                store.getCreatedAt(),
                store.getUpdatedAt(),
                store.getMember().getId(),
                store.getMember().getName()
        );
    }
}
