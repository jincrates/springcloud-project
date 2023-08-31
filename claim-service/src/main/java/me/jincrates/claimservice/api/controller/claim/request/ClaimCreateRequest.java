package me.jincrates.claimservice.api.controller.claim.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.jincrates.claimservice.domain.claim.ClaimReason;
import me.jincrates.claimservice.domain.claim.ClaimType;
import org.hibernate.validator.constraints.Length;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClaimCreateRequest {

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

    private Integer videoId;

    @NotNull(message = "수거지 정보는 필수입니다.")
    private DeliveryInfoRequest collectionDelivery;

    private DeliveryInfoRequest exchangeDelivery;

    @Builder
    private ClaimCreateRequest(Long orderId, ClaimType type, ClaimReason reason, String memo,
        List<ClaimProductRequest> claimProducts, List<Long> imageIdList, Integer videoId,
        DeliveryInfoRequest collectionDelivery, DeliveryInfoRequest exchangeDelivery) {
        this.orderId = orderId;
        this.type = type;
        this.reason = reason;
        this.memo = memo;
        this.claimProducts = claimProducts;
        this.imageIdList = imageIdList;
        this.videoId = videoId;
        this.collectionDelivery = collectionDelivery;
        this.exchangeDelivery = exchangeDelivery;
    }

    public void validate() {
        if (memo == null) {
            throw new IllegalArgumentException("상세 사유는 필수 입니다.");
        }

        if (this.reason.isProductDefects() && this.imageIdList == null) {
            throw new IllegalArgumentException(
                ClaimReason.PRODUCT_DEFECTS.getDescription() + "인 경우 첨부 이미지는 필수입니다.");
        }

        if (this.imageIdList != null && this.imageIdList.size() > 5) {
            throw new IllegalArgumentException("첨부 이미지는 최대 5개까지만 등록 가능합니다.");
        }

        if (this.type.isExchange() && this.exchangeDelivery == null) {
            throw new IllegalArgumentException("교환 배송지를 입력하지 않았습니다.");
        }
    }

    public boolean isPayDelivery() {
        return this.getType().isExchange() && this.getReason().isBuyerResponsibility();
    }
}
