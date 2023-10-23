package me.jincrates.ecommerce.store.adapter.persistence;

import lombok.RequiredArgsConstructor;
import me.jincrates.ecommerce.store.application.port.StorePort;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class StoreAdapter implements StorePort {

    private final StoreRepository storeRepository;

}
