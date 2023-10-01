package me.jincrates.ecommerce.payment.domain;


import lombok.Getter;

@Getter
public enum PaymentMethod {
    NONE("결제수단 없음"),
    KAKAO_PAY("카카오페이"),
    NAVER_PAY("네이버페이"),
    SETTLE_PAY("내통장결제"),
    TOSS_PAY("토스페이"),
    PORT_ONE("포트원"),
    ;

    private final String description;

    PaymentMethod(String description) {
        this.description = description;
    }
}
