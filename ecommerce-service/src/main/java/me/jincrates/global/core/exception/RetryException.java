package me.jincrates.global.core.exception;

import lombok.Getter;

@Getter
public class RetryException extends RuntimeException {

    private String message;

    public RetryException(String message) {
        this.message = message;
    }
}
