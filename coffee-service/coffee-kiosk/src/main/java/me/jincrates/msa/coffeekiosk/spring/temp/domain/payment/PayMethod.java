package me.jincrates.msa.coffeekiosk.spring.temp.domain.payment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PayMethod {
    SETTLE_BANK("내통장결제"),
    TOSS_PAY("토스페이");

    private final String text;
}
