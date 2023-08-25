package me.jincrates.claimservice.domain.delivery.collection;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.claimservice.domain.BaseEntity;
import me.jincrates.claimservice.domain.delivery.DeliveryInfo;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "COLLECTION_DELIVERY")
public class CollectionDelivery extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long claimId;
    private String statusCode;
    private String deliveryMethodCode;
    private String recipientName;
    private String recipientMobileNo;
    private String recipientZipCode;
    private String recipientAddress1;
    private String recipientAddress2;
    private String recipientRoadAddress;
    private String recipientLandAddress;

    @Builder(access = AccessLevel.PRIVATE)
    private CollectionDelivery(Long claimId, String statusCode, String deliveryMethodCode,
        String recipientName, String recipientMobileNo, String recipientZipCode,
        String recipientAddress1, String recipientAddress2, String recipientRoadAddress,
        String recipientLandAddress) {
        this.claimId = claimId;
        this.statusCode = statusCode;
        this.deliveryMethodCode = deliveryMethodCode;
        this.recipientName = recipientName;
        this.recipientMobileNo = recipientMobileNo;
        this.recipientZipCode = recipientZipCode;
        this.recipientAddress1 = recipientAddress1;
        this.recipientAddress2 = recipientAddress2;
        this.recipientRoadAddress = recipientRoadAddress;
        this.recipientLandAddress = recipientLandAddress;
    }

    public static CollectionDelivery create(Long claimId, DeliveryInfo deliveryInfo) {
        return CollectionDelivery.builder()
            .claimId(claimId)
            .statusCode("10")
            .deliveryMethodCode(deliveryInfo.getDeliveryMethodCode())
            .recipientName(deliveryInfo.getRecipient().getName())
            .recipientMobileNo(deliveryInfo.getRecipient().getMobileNo())
            .recipientZipCode(deliveryInfo.getAddress().getZipCode())
            .recipientAddress1(deliveryInfo.getAddress().getAddress1())
            .recipientAddress2(deliveryInfo.getAddress().getAddress2())
            .recipientRoadAddress(deliveryInfo.getAddress().getRoadAddress())
            .recipientLandAddress(deliveryInfo.getAddress().getLandAddress())
            .build();
    }
}
