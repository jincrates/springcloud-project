package me.jincrates.claimservice.domain.delivery;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import me.jincrates.claimservice.api.controller.request.DeliveryInfoRequest;
import me.jincrates.claimservice.domain.user.Recipient;

@Getter
public final class DeliveryInfo {

    private final Recipient recipient;
    private final Address address;
    private final String deliveryMethodCode;

    @Builder(access = AccessLevel.PRIVATE)
    private DeliveryInfo(Recipient recipient, Address address, String deliveryMethodCode) {
        this.recipient = recipient;
        this.address = address;
        this.deliveryMethodCode = deliveryMethodCode;
    }

    public static DeliveryInfo of(DeliveryInfoRequest request, Long userId) {
        return DeliveryInfo.builder()
            .recipient(
                Recipient.builder()
                    .userId(userId)
                    .name(request.getRecipientName())
                    .mobileNo(request.getRecipientMobileNo())
                    .build()
            )
            .address(
                Address.builder()
                    .build()
            )
            .deliveryMethodCode(request.getDeliveryEnterMethodCode())
            .build();
    }
}
