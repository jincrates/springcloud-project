package me.jincrates.ecommerce.store.application.service;

import lombok.RequiredArgsConstructor;
import me.jincrates.ecommerce.member.application.port.MemberPort;
import me.jincrates.ecommerce.member.domain.Member;
import me.jincrates.ecommerce.store.application.port.StorePort;
import me.jincrates.ecommerce.store.application.port.StoreUseCase;
import me.jincrates.ecommerce.store.application.service.request.StoreCreateRequest;
import me.jincrates.ecommerce.store.application.service.request.StoreUpdateRequest;
import me.jincrates.ecommerce.store.application.service.response.StoreResponse;
import me.jincrates.ecommerce.store.domain.Store;
import me.jincrates.global.common.enumtype.FileBucket;
import me.jincrates.global.common.file.application.port.FilePort;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreService implements StoreUseCase {

    private final MemberPort memberPort;
    private final StorePort storePort;
    private final FilePort filePort;

    @Override
    @Transactional
    public StoreResponse createStore(StoreCreateRequest request, Long memberId) {
        Member member = memberPort.findMemberById(memberId);
        Store store = Store.create(member, request.name(), request.description(), request.address(),
                request.imageUrls());
        storePort.saveStore(store);
        filePort.uploadImages(request.imageUrls(), memberId, FileBucket.STORE);

        return StoreResponse.of(store);
    }

    @Override
    public List<StoreResponse> getStores(Pageable pageable) {
        List<Store> stores = storePort.findAllStore(pageable);
        return stores.stream()
                .map(StoreResponse::of)
                .toList();
    }

    @Override
    public StoreResponse getStoreById(Long storeId) {
        Store store = storePort.findStoreById(storeId);
        return StoreResponse.of(store);
    }

    @Override
    @Transactional
    public StoreResponse updateStore(StoreUpdateRequest request, Long storeId, Long memberId) {
        Store store = storePort.findStoreByIdAndMemberId(storeId, memberId);
        store.update(store.getName(), store.getDescription(), store.getAddress(),
                store.getImageUrls());

        Store updatedStore = storePort.saveStore(store);
        return StoreResponse.of(updatedStore);
    }

    @Override
    @Transactional
    public StoreResponse makeStoreInactive(Long storeId, Long memberId) {
        Store store = storePort.findStoreByIdAndMemberId(storeId, memberId);
        store.setInactive();

        Store updatedStore = storePort.saveStore(store);
        return StoreResponse.of(updatedStore);
    }
}
