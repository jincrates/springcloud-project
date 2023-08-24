package me.jincrates.claimservice.api.service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jincrates.claimservice.api.controller.request.ClaimApprovalRequest;
import me.jincrates.claimservice.api.controller.request.ClaimCreateRequest;
import me.jincrates.claimservice.api.controller.request.ClaimProductRequest;
import me.jincrates.claimservice.api.controller.request.ClaimRejectRequest;
import me.jincrates.claimservice.api.controller.request.ClaimWithdrawalRequest;
import me.jincrates.claimservice.api.controller.response.ClaimResponse;
import me.jincrates.claimservice.domain.claim.Claim;
import me.jincrates.claimservice.domain.claim.ClaimRepository;
import me.jincrates.claimservice.domain.claim.ClaimStatus;
import me.jincrates.claimservice.domain.orderproduct.OrderProduct;
import me.jincrates.claimservice.domain.orderproduct.OrderProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClaimService {

    private final ClaimRepository claimRepository;
    private final OrderProductRepository orderProductRepository;

    // 클레임 접수
    @Transactional
    public ClaimResponse request(ClaimCreateRequest request) {
        log.info("클레임을 접수합니다. >>> {}", request);
        // 주문상품 id list 조회
        List<Long> orderProductIds = request.getClaimProducts()
            .stream()
            .map(ClaimProductRequest::getOrderProductId)
            .toList();

        List<OrderProduct> orderProducts = orderProductRepository.findAllById(orderProductIds);
        Map<Long, OrderProduct> orderProductMap = createOrderProductMapBy(orderProducts);

        for (ClaimProductRequest cp : request.getClaimProducts()) {
            OrderProduct orderProduct = orderProductMap.get(cp.getOrderProductId());
            if (orderProduct.getQuantity() < cp.getQuantity()) {
                throw new IllegalArgumentException("클레임 접수 수량은 주문 수량보다 클 수 없습니다.");
            }
        }

        // 클레임 저장
        Claim claim = Claim.create(request.getOrderId(), request.getType(), request.getReason(),
            ClaimStatus.REQUESTED, request.getMemo(), orderProducts);
        Claim savedClaim = claimRepository.save(claim);

        return ClaimResponse.of(savedClaim);
    }

    // 클레임 상태변경
    // 클레임 ID, 변경할 상태
    @Transactional
    public Long cancel(ClaimWithdrawalRequest request) {
        Claim claim = claimRepository.findById(request.getClaimId())
            .orElseThrow(() -> new EntityNotFoundException("클레임을 찾을 수 없습니다."));

        claim.cancel();

        return claim.getId();
    }

    @Transactional
    public Long approve(ClaimApprovalRequest request) {
        Claim claim = claimRepository.findById(request.getClaimId())
            .orElseThrow(() -> new EntityNotFoundException("클레임을 찾을 수 없습니다."));

        claim.approve();

        return claim.getId();
    }

    @Transactional
    public Long reject(ClaimRejectRequest request) {
        Claim claim = claimRepository.findById(request.getClaimId())
            .orElseThrow(() -> new EntityNotFoundException("클레임을 찾을 수 없습니다."));

        claim.reject(request.getRejectMemo());

        return claim.getId();
    }

    private Map<Long, OrderProduct> createOrderProductMapBy(List<OrderProduct> orderProducts) {
        return orderProducts.stream()
            .collect(Collectors.toMap(OrderProduct::getId, op -> op));
    }
}
