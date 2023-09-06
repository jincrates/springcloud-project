package me.jincrates.api.ecommerce.domain.order;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static me.jincrates.api.ecommerce.domain.order.QOrder.order;

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

    @Override
    public Page<Order> findOrdersPage(String email, Pageable pageable) {
        List<Order> contents = queryFactory
                .selectFrom(order)
                .where(order.member.email.eq(email))
                .orderBy(order.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(order.count())
                .from(order)
                .where(order.member.email.eq(email));

        return PageableExecutionUtils.getPage(contents, pageable, countQuery::fetchOne);
    }
}
