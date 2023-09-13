package me.jincrates.api.ecommerce.orders.domain;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static me.jincrates.api.ecommerce.orders.domain.QOrder.order;

@RequiredArgsConstructor
public class OrderQueryRepositoryImpl implements OrderQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Order> findOrders(String email, Pageable pageable) {
        return queryFactory.selectFrom(order)
            .where(order.member.email.eq(email))
            .orderBy(order.createdAt.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize() + 1)
            .fetch();
    }

    @Override
    public Long countOrder(String email) {
        return queryFactory.select(order.count())
            .from(order)
            .where(order.member.email.eq(email))
            .fetchOne();
    }
}
