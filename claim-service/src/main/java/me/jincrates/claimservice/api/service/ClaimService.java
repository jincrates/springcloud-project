package me.jincrates.claimservice.api.service;

import lombok.RequiredArgsConstructor;
import me.jincrates.claimservice.api.controller.request.ClaimCreateRequest;
import me.jincrates.claimservice.api.controller.response.ClaimResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClaimService {

    // 클레임 접수
    //주문번호, 주문상품 리스트 {id, 수량}, 클레임유형, 사유, 상세사유
    public ClaimResponse createClaim(ClaimCreateRequest request) {
        // 주문상품 id list 조회

        // 수량 매핑

        // 클레임 저장

        return null;
    }

}
