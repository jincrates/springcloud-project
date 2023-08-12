package com.example.claimdemo.api.service;

import com.example.claimdemo.api.service.request.ClaimCreateServiceRequest;
import com.example.claimdemo.api.service.request.OrderProductServiceRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ClaimService {

    //주문번호, 주문상품 리스트 {id, 수량}, 클레임유형, 사유, 상세사유
    private void createClaim(ClaimCreateServiceRequest request) {
        // 주문상품 id list 조회
        List<Long> orderProductIds = request.getOrderProducts().stream()
            .map(OrderProductServiceRequest::getOrderProductId)
            .toList();

//        List<OrderProduct> orderProducts = orderProductRepository.findByOrderIdAndIdIn(
//            request.getOrderId(), orderProductIds);

        // 클레임 저장
    }
}
