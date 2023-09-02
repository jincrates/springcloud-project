package me.jincrates.api.claims.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.jincrates.api.claims.api.controller.request.*;
import me.jincrates.api.claims.domain.claim.ClaimReason;
import me.jincrates.api.claims.domain.claim.ClaimType;
import me.jincrates.api.claims.api.controller.response.ClaimResponse;
import me.jincrates.api.claims.api.service.ClaimService;
import me.jincrates.api.global.common.response.CommonResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClaimController {

    private final ClaimService claimService;

    @GetMapping("/api/v1/claims/test")
    public CommonResponse<ClaimResponse> test() {
        ClaimCreateRequest request = getData(ClaimType.EXCHANGE, ClaimReason.CHANGE_MIND);
        request.validate();

        Long userId = 1L;

        return CommonResponse.ok(claimService.request(request, userId));
    }

    //

    @PostMapping("/api/v1/claims/return/request")
    public CommonResponse<ClaimResponse> returnRequest(
            @Valid @RequestBody ClaimCreateRequest request
    ) {
        request.validate();

        Long userId = 1L;

        return CommonResponse.ok(claimService.request(request, userId));
    }

    @PostMapping("/api/v1/claims/exchange/request")
    public CommonResponse<ClaimResponse> exchangeRequest(
            @Valid @RequestBody ClaimCreateRequest request
    ) {
        request.validate();

        Long userId = 1L;

        return CommonResponse.ok(claimService.request(request, userId));
    }


    @PostMapping("/api/v1/claims/withdrawal")
    public CommonResponse<Long> withdrawal(
            @Valid @RequestBody ClaimWithdrawalRequest request
    ) {
        return CommonResponse.ok(claimService.cancel(request));
    }

    @PostMapping("/api/v1/claims/approval")
    public CommonResponse<Long> approval(
            @Valid @RequestBody ClaimApprovalRequest request
    ) {
        return CommonResponse.ok(claimService.approve(request));
    }

    @PostMapping("/api/v1/claims/reject")
    public CommonResponse<Long> reject(
            @Valid @RequestBody ClaimRejectRequest request
    ) {
        return CommonResponse.ok(claimService.reject(request));
    }

    private ClaimCreateRequest getData(ClaimType type, ClaimReason reason) {
        return ClaimCreateRequest.builder()
                .orderId(1L)
                .type(type)
                .reason(reason)
                .memo(String.format("%s(으)로 인한 %s입니다.", reason.getDescription(), type.getDescription()))
                .claimProducts(List.of(
                                ClaimProductRequest.builder()
                                        .orderProductId(1L)
                                        .quantity(5)
                                        .build(),
                                ClaimProductRequest.builder()
                                        .orderProductId(2L)
                                        .quantity(10)
                                        .build(),
                                ClaimProductRequest.builder()
                                        .orderProductId(3L)
                                        .quantity(15)
                                        .build()
                        )
                )
                //.imageIdList(null)
                .collectionDelivery(
                        DeliveryInfoRequest.builder()
                                .recipientName("김칸트")
                                .recipientMobileNo("01012345678")
                                .zipCode("06038")
                                .address1("서울특별시 도산대로 4길 15(논현동)")
                                .address2("5층")
                                .roadAddress("서울특별시 강남구 도산대로 4길 15(논현동)")
                                .landAddress("서울특별시 강남구 논현동 16-23 큐브타워")
                                .deliveryTypeCode("V")
                                .deliveryEnterMethodCode("E")
                                .deliveryEnterMethodMessage("창문을 통해 들어오세요.")
                                .build()
                )
                .exchangeDelivery(
                        DeliveryInfoRequest.builder()
                                .recipientName("김칸트")
                                .recipientMobileNo("01012345678")
                                .zipCode("06038")
                                .address1("서울특별시 도산대로 4길 15(논현동)")
                                .address2("5층")
                                .roadAddress("서울특별시 강남구 도산대로 4길 15(논현동)")
                                .landAddress("서울특별시 강남구 논현동 16-23 큐브타워")
                                .deliveryTypeCode("V")
                                .deliveryEnterMethodCode("E")
                                .deliveryEnterMethodMessage("창문을 통해 들어오세요.")
                                .build())
                .build();
    }
}
