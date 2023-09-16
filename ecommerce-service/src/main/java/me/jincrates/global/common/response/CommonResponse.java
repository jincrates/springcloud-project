package me.jincrates.global.common.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
        return of(httpStatus, null, data);
    }

    public static <T> CommonResponse<T> ok(T data) {
        return of(HttpStatus.OK, data);
    }

    public static <T> CommonResponse<T> created(T data) {
        return of(HttpStatus.CREATED, data);
    }

    public static ResponseEntity<?> toResponseEntity(HttpStatus status, String message) {
        return ResponseEntity.status(status)
                .body(CommonResponse.of(status, message, null));
    }
}