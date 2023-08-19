package me.jincrates.claimservice.api.controller.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DeliveryInfoRequest {
    private String recipientName;
    private String recipientMobileNo;
    private String zipCode;
    private String address1;
    private String address2;
    private String roadAddress;
    private String landAddress;
    private String deliveryTypeCode;
    private String deliveryEnterMethodCode;
    private String deliveryEnterMethodMessage;

    @Builder
    private DeliveryInfoRequest(String recipientName, String recipientMobileNo, String zipCode, String address1, String address2, String roadAddress, String landAddress, String deliveryTypeCode, String deliveryEnterMethodCode, String deliveryEnterMethodMessage) {
        if (!Pattern.matches("^[0-9]+$", recipientMobileNo)) {
            throw new IllegalArgumentException("핸드폰 번호는 -를 빼고 숫자만 입력해주세요.");
        }

        this.recipientName = recipientName;
        this.recipientMobileNo = recipientMobileNo;
        this.zipCode = zipCode;
        this.address1 = address1;
        this.address2 = address2;
        this.roadAddress = roadAddress;
        this.landAddress = landAddress;
        this.deliveryTypeCode = deliveryTypeCode;
        this.deliveryEnterMethodCode = deliveryEnterMethodCode;
        this.deliveryEnterMethodMessage = deliveryEnterMethodMessage;
    }
}
