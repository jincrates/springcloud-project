package me.jincrates.ecommerce.retry.application;

import java.util.function.Supplier;

public interface RetryPort {

    <T> T executeWithRetry(Supplier<T> action);
}
