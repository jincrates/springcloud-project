package me.jincrates.msa.coffeekiosk.spring.global.common.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CommonResponse<T> {

    private int code;
    private HttpStatus status;
    private String message;
    private T data;

    private CommonResponse(HttpStatus status, String message, T data) {
        this.code = status.value();
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static <T> CommonResponse<T> of(HttpStatus httpStatus, String message, T data) {
        return new CommonResponse<>(httpStatus, message, data);
    }

    public static <T> CommonResponse<T> of(HttpStatus httpStatus, T data) {
        return of(httpStatus, httpStatus.name(), data);
    }

    public static <T> CommonResponse<T> ok(T data) {
        return of(HttpStatus.OK, data);
    }
}
