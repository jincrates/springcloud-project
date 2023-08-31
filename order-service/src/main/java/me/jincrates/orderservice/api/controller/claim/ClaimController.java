package me.jincrates.orderservice.api.controller.claim;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.jincrates.orderservice.api.ApiResponse;
import me.jincrates.orderservice.api.controller.claim.request.*;
import me.jincrates.orderservice.api.controller.claim.response.ClaimResponse;
import me.jincrates.orderservice.api.service.claim.ClaimService;
import me.jincrates.orderservice.domain.claim.ClaimReason;
import me.jincrates.orderservice.domain.claim.ClaimType;
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
    public ApiResponse<ClaimResponse> test() {
        ClaimCreateRequest request = getData(ClaimType.EXCHANGE, ClaimReason.CHANGE_MIND);
        request.validate();

        Long userId = 1L;

        return ApiResponse.ok(claimService.request(request, userId));
    }

    //

    @PostMapping("/api/v1/claims/return/request")
    public ApiResponse<ClaimResponse> returnRequest(
            @Valid @RequestBody ClaimCreateRequest request
    ) {
        request.validate();

        Long userId = 1L;

        return ApiResponse.ok(claimService.request(request, userId));
    }

    @PostMapping("/api/v1/claims/exchange/request")
    public ApiResponse<ClaimResponse> exchangeRequest(
            @Valid @RequestBody ClaimCreateRequest request
    ) {
        request.validate();

        Long userId = 1L;

        return ApiResponse.ok(claimService.request(request, userId));
    }


    @PostMapping("/api/v1/claims/withdrawal")
    public ApiResponse<Long> withdrawal(
            @Valid @RequestBody ClaimWithdrawalRequest request
    ) {
        return ApiResponse.ok(claimService.cancel(request));
    }

    @PostMapping("/api/v1/claims/approval")
    public ApiResponse<Long> approval(
            @Valid @RequestBody ClaimApprovalRequest request
    ) {
        return ApiResponse.ok(claimService.approve(request));
    }

    @PostMapping("/api/v1/claims/reject")
    public ApiResponse<Long> reject(
            @Valid @RequestBody ClaimRejectRequest request
    ) {
        return ApiResponse.ok(claimService.reject(request));
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
