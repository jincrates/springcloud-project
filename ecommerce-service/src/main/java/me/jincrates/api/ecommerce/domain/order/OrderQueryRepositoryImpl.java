package me.jincrates.api.ecommerce.domain.order;

import static me.jincrates.api.ecommerce.domain.order.QOrder.order;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class OrderQueryRepositoryImpl implements OrderQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Order> findOrders(String email, Pageable pageable) {
        return queryFactory.selectFrom(order)
            .where(order.member.email.eq(email))
            .orderBy(order.createdAt.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
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
