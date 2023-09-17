package me.jincrates.ecommerce.retry.adapter;

import jakarta.persistence.OptimisticLockException;
import lombok.extern.slf4j.Slf4j;
import me.jincrates.ecommerce.retry.application.RetryPort;
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
            } catch (OptimisticLockException lockException) {
                attempt++;
                if (attempt == MAX_RETRY) {
                    throw new OptimisticLockException("최대 재시도 후 작업을 싱행하지 못하였습니다.");
                }
            }
        }

        throw new RuntimeException("작업을 재시도 하지 못하였습니다.");
    }
}
