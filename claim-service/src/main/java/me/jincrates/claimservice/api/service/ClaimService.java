package me.jincrates.claimservice.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jincrates.claimservice.api.controller.request.ClaimCreateRequest;
import me.jincrates.claimservice.api.controller.request.ClaimProductRequest;
import me.jincrates.claimservice.api.controller.response.ClaimResponse;
import me.jincrates.claimservice.domain.claim.Claim;
import me.jincrates.claimservice.domain.claim.ClaimRepository;
import me.jincrates.claimservice.domain.orderproduct.OrderProduct;
import me.jincrates.claimservice.domain.orderproduct.OrderProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClaimService {

    private final ClaimRepository claimRepository;
    private final OrderProductRepository orderProductRepository;

    // 클레임 접수
    public ClaimResponse receipt(ClaimCreateRequest request) {
        log.info("클레임을 접수합니다. >>> {}", request);
        // 주문상품 id list 조회
        List<Long> orderProductIds = request.getClaimProductRequests()
                .stream()
                .map(ClaimProductRequest::getOrderProductId)
                .toList();

        List<OrderProduct> orderProducts = orderProductRepository.findAllById(orderProductIds);
        Map<Long, OrderProduct> orderProductMap = createOrderProductMapBy(orderProducts);

        for (ClaimProductRequest cp : request.getClaimProductRequests()) {
            OrderProduct orderProduct = orderProductMap.get(cp.getOrderProductId());
            if (orderProduct.getQuantity() < cp.getQuantity()) {
                throw new IllegalArgumentException("클레임 접수 수량은 주문 수량보다 클 수 없습니다.");
            }
        }

        // 클레임 저장
        Claim claim = Claim.create(request.getOrderId(), request.getType(), request.getReason(), request.getMemo(), orderProducts);
        Claim savedClaim = claimRepository.save(claim);

        return ClaimResponse.of(savedClaim);
    }

    // 클레임 상태변경
    // 클레임 ID, 변경할 상태
    public void updateClaimStatus() {

    }

    private Map<Long, OrderProduct> createOrderProductMapBy(List<OrderProduct> orderProducts) {
        return orderProducts.stream()
                .collect(Collectors.toMap(OrderProduct::getId, op -> op));
    }

}
