package me.jincrates.claimservice.domain.order.custom;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import me.jincrates.claimservice.domain.order.custom.response.OrderProductRepositoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class OrderCustomRepositoryImpl implements OrderCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<OrderProductRepositoryResponse> getOrderProductList(Long userId, Long orderId,
        Pageable pageable) {

//        QOrder O = QOrder.order;
//
//        QueryResults<OrderProductRepositoryResponse> results = queryFactory.select(
//            new OrderProductRepositoryResponse(
//
//            )
//        )
//            .from

        return null;
    }
}
