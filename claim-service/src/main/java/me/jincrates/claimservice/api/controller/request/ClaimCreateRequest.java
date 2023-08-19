package me.jincrates.claimservice.api.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import me.jincrates.claimservice.domain.claim.ClaimReason;
import me.jincrates.claimservice.domain.claim.ClaimType;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClaimCreateRequest {

    //주문번호, 주문상품 리스트 {id, 수량}, 클레임유형, 사유, 상세사유
    @NotNull(message = "주문 번호는 필수입니다.")
    private Long orderId;
    @NotNull(message = "접수 유형은 필수입니다.")
    private ClaimType type;
    @NotNull(message = "접수 사유는 필수입니다.")
    private ClaimReason reason;
    @NotBlank(message = "상세 사유는 필수입니다.")
    @Length(max = 500, message = "상세 사유는 최대 500자까지만 작성 가능합니다.")
    private String memo;
    private List<ClaimProductRequest> claimProducts;
    @Size(max = 5, message = "첨부 이미지는 최대 5개까지만 등록 가능합니다.")
    private List<Long> imageIdList = new ArrayList<>();
    @NotNull(message = "수거지 정보는 필수입니다.")
    private DeliveryInfoRequest collectionDelivery;
    private DeliveryInfoRequest exchangeDelivery;

    @Builder
    private ClaimCreateRequest(Long orderId, ClaimType type, ClaimReason reason, String memo,
                               List<ClaimProductRequest> claimProducts, List<Long> imageIdList,
                               DeliveryInfoRequest collectionDelivery, DeliveryInfoRequest exchangeDelivery) {

        if (reason != null && reason.isProductDefects()) {
            if (imageIdList == null) {
                throw new IllegalArgumentException(ClaimReason.PRODUCT_DEFECTS.getDescription() + "인 경우 첨부 이미지는 필수입니다.");
            }
        }

        if (imageIdList != null) {
            if (imageIdList.size() > 5) {
                throw new IllegalArgumentException("첨부 이미지는 최대 5개까지만 등록 가능합니다.");
            }
        }

        if (type != null && type.isExchange()) {
            if (exchangeDelivery == null) {
                throw new IllegalArgumentException("교환 배송지를 입력하지 않았습니다.");
            }
        }

        this.orderId = orderId;
        this.type = type;
        this.reason = reason;
        this.memo = memo;
        this.claimProducts = claimProducts;
        this.collectionDelivery = collectionDelivery;
        this.exchangeDelivery = exchangeDelivery;
    }
}
