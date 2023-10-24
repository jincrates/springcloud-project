package me.jincrates.ecommerce.store.adapter.persistence;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import me.jincrates.ecommerce.store.application.port.StorePort;
import me.jincrates.ecommerce.store.domain.Store;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class StoreAdapter implements StorePort {

    private final StoreRepository storeRepository;

    @Override
    public void deleteAllStoreInBatch() {
        storeRepository.deleteAllInBatch();
    }

    @Override
    public Store saveStore(Store store) {
        return storeRepository.save(store);
    }

    @Override
    public List<Store> findAllStore(Pageable pageable) {
        return storeRepository.findAll(pageable).getContent();
    }

    @Override
    public Store findStoreById(Long storeId) {
        return storeRepository.findById(storeId)
            .orElseThrow(() -> new EntityNotFoundException("상점을 찾을 수 없습니다. storeId=" + storeId));
    }

    @Override
    public Store findStoreByIdAndMemberId(Long storeId, Long memberId) {
        return storeRepository.findByIdAndMemberId(storeId, memberId)
            .orElseThrow(() -> new EntityNotFoundException(
                "상점을 찾을 수 없습니다. storeId=" + storeId + ", memberId=" + memberId));
    }
}
