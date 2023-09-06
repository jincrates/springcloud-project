package me.jincrates.api.ecommerce.domain.product;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import me.jincrates.api.ecommerce.api.service.request.ProductSearchServiceRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.time.LocalDateTime;
import java.util.List;

import static me.jincrates.api.ecommerce.domain.product.QProduct.product;

@RequiredArgsConstructor
public class ProductQueryRepositoryImpl implements ProductQueryRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Product> getAdminProductPage(ProductSearchServiceRequest request, Pageable pageable) {

        List<Product> contents = queryFactory
                .selectFrom(product)
                .where(createdAtAfter(request.getSearchDateType()),
                        searchStatusEq(request.getSearchStatus()),
                        searchByLike(request.getSearchBy(), request.getSearchQuery()))
                .orderBy(product.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(product.count())
                .where(createdAtAfter(request.getSearchDateType()),
                        searchStatusEq(request.getSearchStatus()),
                        searchByLike(request.getSearchBy(), request.getSearchQuery()));

        return PageableExecutionUtils.getPage(contents, pageable, countQuery::fetchOne);
    }

    private BooleanExpression createdAtAfter(String searchDateType) {
        LocalDateTime dateTime = LocalDateTime.now();

        if ("all".equals(searchDateType) || searchDateType == null) {
            return null;
        }
        if ("1d".equals(searchDateType)) {
            return product.createdAt.after(dateTime.minusDays(1));
        }
        if ("1w".equals(searchDateType)) {
            return product.createdAt.after(dateTime.minusWeeks(1));
        }
        if ("1m".equals(searchDateType)) {
            return product.createdAt.after(dateTime.minusMonths(1));
        }
        if ("6m".equals(searchDateType)) {
            return product.createdAt.after(dateTime.minusMonths(6));
        }

        return product.createdAt.after(dateTime);
    }

    private BooleanExpression searchStatusEq(ProductSellingStatus searchStatus) {
        return searchStatus == null ? null : product.status.eq(searchStatus);
    }

    private Predicate searchByLike(String searchBy, String searchQuery) {
        if ("productName".equals(searchBy)) {
            return product.productName.like("%" + searchQuery + "%");
        }
        if ("createdBy".equals(searchBy)) {
            return product.createdBy.like("%" + searchQuery + "%");
        }
        return null;
    }


}
