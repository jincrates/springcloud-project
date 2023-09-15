package me.jincrates.global.core.exception.handler;

import lombok.extern.slf4j.Slf4j;
import me.jincrates.global.common.response.CommonResponse;
import me.jincrates.global.core.exception.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.nio.file.AccessDeniedException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handlerIllegalArgumentException(final IllegalArgumentException exception) {
        log.warn("IllegalArgumentException", exception);
        return CommonResponse.toResponseEntity(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<?> handlerBindException(final BindException exception) {
        log.warn("BindException", exception);
        return CommonResponse.toResponseEntity(HttpStatus.BAD_REQUEST, exception.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    /**
     * javax.validation.Valid or @Validated 으로 binding error 발생시 발생한다.
     * HttpMessageConverter 에서 등록한 HttpMessageConverter binding 못할경우 발생
     * 주로 @RequestBody, @RequestPart 어노테이션에서 발생
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<?> handleMethodArgumentNotValidException(final MethodArgumentNotValidException exception) {
        String message = getMessageFromBindingResult(exception.getBindingResult());
        log.warn("MethodArgumentNotValidException: {}", message, exception);

        return CommonResponse.toResponseEntity(HttpStatus.BAD_REQUEST, message);
    }

    /**
     * enum type 일치하지 않아 binding 못할 경우 발생
     * 주로 @RequestParam enum으로 binding 못했을 경우 발생
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<?> handlerMethodArgumentTypeMismatchException(final MethodArgumentTypeMismatchException exception) {
        log.warn("MethodArgumentTypeMismatchException", exception);
        return CommonResponse.toResponseEntity(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    /**
     * 지원하지 않은 HTTP method 호출 할 경우 발생
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<?> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        log.warn("HttpRequestMethodNotSupportedException", exception);
        return CommonResponse.toResponseEntity(HttpStatus.METHOD_NOT_ALLOWED, exception.getMessage());
    }

    /**
     * Authentication 객체가 필요한 권한을 보유하지 않은 경우 발생합
     */
    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<?> handleAccessDeniedException(AccessDeniedException exception) {
        log.warn("AccessDeniedException", exception);
        return CommonResponse.toResponseEntity(HttpStatus.FORBIDDEN, exception.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<?> handleBadRequestException(BadRequestException exception) {
        log.warn("BadRequestException: {}", exception.getMessage());
        return CommonResponse.toResponseEntity(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    /**
     * RestTemplate API 클라이언트 예외 처리
     */
    @ExceptionHandler(HttpClientErrorException.class)
    protected ResponseEntity<?> handleHttpClientErrorException(HttpClientErrorException exception) {
        log.error("HttpClientErrorException", exception);
        return CommonResponse.toResponseEntity(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    /**
     * RestTemplate API 서버 예외 처리
     */
    @ExceptionHandler(HttpServerErrorException.class)
    protected ResponseEntity<?> handleHttpServerErrorException(HttpServerErrorException exception) {
        log.error("HttpServerErrorException", exception);
        return CommonResponse.toResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
    }

    /**
     * RestTemplate API 클라이언트 예외 처리
     */
    @ExceptionHandler(RestClientException.class)
    protected ResponseEntity<?> handleHttpServerErrorException(RestClientException exception) {
        log.error("RestClientException", exception);
        return CommonResponse.toResponseEntity(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    /**
     * Exception error
     * 예상하지 못한 에러, Sentry 으로도 전송 됨.
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<?> handleException(Exception exception) {
        log.error("Exception", exception);
        return CommonResponse.toResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }

    private String getMessageFromBindingResult(BindingResult bindingResult) {
        FieldError error = bindingResult.getFieldErrors().get(0);

        StringBuilder builder = new StringBuilder();
        builder.append(error.getField()).append("(은)는 ");
        builder.append(error.getDefaultMessage());
        builder.append(" 입력된 값: [").append(error.getRejectedValue()).append("]");

        return builder.toString();
    }
}
