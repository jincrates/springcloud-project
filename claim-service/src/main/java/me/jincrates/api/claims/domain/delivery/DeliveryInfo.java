package me.jincrates.api.claims.domain.delivery;

import lombok.Builder;
import lombok.Getter;
import me.jincrates.api.claims.domain.user.Recipient;

@Getter
public final class DeliveryInfo {

    private final Recipient recipient;
    private final Address address;
    private final DeliveryTypeCode deliveryMethodCode;

    @Builder
    private DeliveryInfo(Recipient recipient, Address address, String deliveryMethodCode) {
        this.recipient = recipient;
        this.address = address;
        this.deliveryMethodCode = DeliveryTypeCode.valueOf(deliveryMethodCode);
    }
}
