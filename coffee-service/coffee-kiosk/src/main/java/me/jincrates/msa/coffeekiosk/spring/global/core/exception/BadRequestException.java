package me.jincrates.msa.coffeekiosk.spring.global.core.exception;

import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException {

    private String message;

    public BadRequestException(String message) {
        this.message = message;
    }
}
