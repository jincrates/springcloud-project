package me.jincrates.ecommerce.product.adapter.persistence;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import me.jincrates.ecommerce.product.application.service.request.ProductSearchRequest;
import me.jincrates.ecommerce.product.domain.Product;
import me.jincrates.ecommerce.product.domain.ProductSellingStatus;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

import static me.jincrates.ecommerce.product.domain.QProduct.product;

@RequiredArgsConstructor
class ProductQueryRepositoryImpl implements ProductQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Product> findAllProduct(ProductSearchRequest request, Pageable pageable) {
        return queryFactory
                .selectFrom(product)
                .where(createdAtAfter(request.searchDateType()),
                        searchStatusEq(request.searchStatus()),
                        searchByLike(request.searchBy(), request.searchQuery()))
                .orderBy(product.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .fetch();
    }

    private BooleanExpression createdAtAfter(String searchDateType) {
        LocalDateTime dateTime = LocalDateTime.now();

        if ("all".equals(searchDateType) || ObjectUtils.isEmpty(searchDateType)) {
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
        return ObjectUtils.isEmpty(searchStatus) ? null : product.sellingStatus.eq(searchStatus);
    }

    private Predicate searchByLike(String searchBy, String searchQuery) {
        if ("productName".equals(searchBy)) {
            return product.name.like("%" + searchQuery + "%");
        }
//        if ("createdBy".equals(searchBy)) {
//            return product.createdBy.like("%" + searchQuery + "%");
//        }
        return null;
    }


}
