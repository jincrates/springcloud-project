package me.jincrates.ecommerce.store.application.service;

import lombok.RequiredArgsConstructor;
import me.jincrates.ecommerce.store.application.port.StorePort;
import me.jincrates.ecommerce.store.application.port.StoreUseCase;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService implements StoreUseCase {

    private final StorePort storePort;
}
