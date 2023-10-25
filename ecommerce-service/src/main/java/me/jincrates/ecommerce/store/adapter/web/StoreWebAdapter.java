package me.jincrates.ecommerce.store.adapter.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.jincrates.ecommerce.store.application.port.StoreUseCase;
import me.jincrates.ecommerce.store.application.service.request.StoreCreateRequest;
import me.jincrates.ecommerce.store.application.service.request.StoreUpdateRequest;
import me.jincrates.ecommerce.store.application.service.response.StoreResponse;
import me.jincrates.global.common.auth.application.AuthPort;
import me.jincrates.global.common.response.CommonResponse;
import me.jincrates.global.common.response.PageResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "상점 서비스", description = "상점 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stores")
public class StoreWebAdapter {

    private final AuthPort authPort;
    private final StoreUseCase storeUseCase;

    @Operation(summary = "상점 등록")
    @Parameter(name = HttpHeaders.AUTHORIZATION, hidden = true, description = "JWT Token", in = ParameterIn.HEADER, required = true)
    @PostMapping()
    public CommonResponse<StoreResponse> createStore(
            @Valid @RequestBody StoreCreateRequest request,
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization
    ) {
        Long memberId = authPort.parseToken(authorization.substring(7));
        return CommonResponse.created(storeUseCase.createStore(request, memberId));
    }

    @Operation(summary = "상점 목록 조회")
    @GetMapping()
    public CommonResponse<PageResponse<StoreResponse>> getStores(
            @RequestParam(name = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "5", required = false) int pageSize
    ) {
        List<StoreResponse> contents = storeUseCase.getStores(PageRequest.of(pageNo, pageSize));
        PageResponse<StoreResponse> response = PageResponse.of(pageNo, pageSize, contents);
        return CommonResponse.ok(response);
    }

    @Operation(summary = "상점 조회")
    @GetMapping("/{storeId}")
    public CommonResponse<StoreResponse> getStore(
            @PathVariable("storeId") Long storeId
    ) {
        return CommonResponse.ok(storeUseCase.getStoreById(storeId));
    }

    @Operation(summary = "상점 수정")
    @Parameter(name = HttpHeaders.AUTHORIZATION, hidden = true, description = "JWT Token", in = ParameterIn.HEADER, required = true)
    @PutMapping("/{storeId}")
    public CommonResponse<StoreResponse> updateStore(
            @Valid @RequestBody StoreUpdateRequest request,
            @PathVariable("storeId") Long storeId,
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization
    ) {
        Long memberId = authPort.parseToken(authorization.substring(7));
        return CommonResponse.ok(storeUseCase.updateStore(request, storeId, memberId));
    }

    @Operation(summary = "상점 비활성화")
    @Parameter(name = HttpHeaders.AUTHORIZATION, hidden = true, description = "JWT Token", in = ParameterIn.HEADER, required = true)
    @PutMapping("/{storeId}/inactive")
    public CommonResponse<StoreResponse> makeStoreInactive(
            @PathVariable("storeId") Long storeId,
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization
    ) {
        Long memberId = authPort.parseToken(authorization.substring(7));
        return CommonResponse.ok(storeUseCase.makeStoreInactive(storeId, memberId));
    }
}