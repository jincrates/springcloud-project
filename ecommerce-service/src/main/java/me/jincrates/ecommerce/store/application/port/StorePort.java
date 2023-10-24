package me.jincrates.ecommerce.store.application.port;

import java.util.List;
import me.jincrates.ecommerce.store.domain.Store;
import org.springframework.data.domain.Pageable;

public interface StorePort {

    void deleteAllStoreInBatch();

    Store saveStore(Store store);

    List<Store> findAllStore(Pageable pageable);

    Store findStoreById(Long storeId);

    Store findStoreByIdAndMemberId(Long storeId, Long memberId);
}
