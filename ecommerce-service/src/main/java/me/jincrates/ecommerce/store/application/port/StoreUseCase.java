package me.jincrates.ecommerce.store.application.port;

import me.jincrates.ecommerce.store.application.service.request.StoreCreateRequest;
import me.jincrates.ecommerce.store.application.service.request.StoreUpdateRequest;
import me.jincrates.ecommerce.store.application.service.response.StoreResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StoreUseCase {

    StoreResponse createStore(StoreCreateRequest request, Long memberId);

    List<StoreResponse> getStores(Pageable pageable);

    StoreResponse getStoreById(Long storeId);

    StoreResponse updateStore(StoreUpdateRequest request, Long storeId, Long memberId);

    StoreResponse makeStoreInactive(Long storeId, Long memberId);
}
