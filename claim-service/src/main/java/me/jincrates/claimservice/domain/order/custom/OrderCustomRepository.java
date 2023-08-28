package me.jincrates.claimservice.domain.order.custom;

import me.jincrates.claimservice.domain.order.custom.response.OrderProductRepositoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderCustomRepository {

    Page<OrderProductRepositoryResponse> getOrderProductList(Long userId, Long orderId,
        Pageable pageable);

}
