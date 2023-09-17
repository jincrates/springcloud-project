package me.jincrates.infra.retry.adapter;

import lombok.extern.slf4j.Slf4j;
import me.jincrates.global.core.exception.RetryException;
import me.jincrates.infra.retry.application.RetryPort;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Slf4j
@Component
class RetryAdapter implements RetryPort {

    private static final int MAX_RETRY = 3;

    @Override
    public <T> T executeWithRetry(Supplier<T> action) {
        int attempt = 0;

        while (attempt < MAX_RETRY) {
            try {
                return action.get();
            } catch (Exception ex) {
                attempt++;
                log.warn("Retry From exception: {}", ex.getMessage());
                if (attempt == MAX_RETRY) {
                    throw new RetryException("최대 재시도 후 작업을 싱행하지 못하였습니다.");
                }
            }
        }

        throw new RetryException("작업을 재시도 하지 못하였습니다.");
    }
}
