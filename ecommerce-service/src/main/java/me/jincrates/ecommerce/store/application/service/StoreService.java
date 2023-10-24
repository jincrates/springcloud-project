package me.jincrates.ecommerce.store.application.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import me.jincrates.ecommerce.member.application.port.MemberPort;
import me.jincrates.ecommerce.member.domain.Member;
import me.jincrates.ecommerce.store.application.port.StorePort;
import me.jincrates.ecommerce.store.application.port.StoreUseCase;
import me.jincrates.ecommerce.store.application.service.request.StoreCreateRequest;
import me.jincrates.ecommerce.store.application.service.request.StoreUpdateRequest;
import me.jincrates.ecommerce.store.application.service.response.StoreResponse;
import me.jincrates.ecommerce.store.domain.Store;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService implements StoreUseCase {

    private final MemberPort memberPort;
    private final StorePort storePort;

    @Override
    public StoreResponse createStore(StoreCreateRequest request, Long memberId) {
        Member member = memberPort.findMemberById(memberId);

        // 이미지 업로드
        Store store = Store.create(member, request.name(), request.description(), request.address(),
            request.imageUrls());
        storePort.saveStore(store);

        return StoreResponse.from(store);
    }

    @Override
    public List<StoreResponse> getStores(Pageable pageable) {
        List<Store> stores = storePort.findAllStore(pageable);
        return stores.stream()
            .map(StoreResponse::from)
            .toList();
    }

    @Override
    public StoreResponse getStoreById(Long storeId) {
        Store store = storePort.findStoreById(storeId);
        return StoreResponse.from(store);
    }

    @Override
    public StoreResponse updateStore(StoreUpdateRequest request, Long storeId, Long memberId) {
        Store store = storePort.findStoreByIdAndMemberId(storeId, memberId);
        store.update(store.getName(), store.getDescription(), store.getAddress(),
            store.getImageUrls());

        Store updatedStore = storePort.saveStore(store);
        return StoreResponse.from(updatedStore);
    }
}
