package me.jincrates.api.ecommerce.domain.cart;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import me.jincrates.api.ecommerce.api.service.response.CartDetailServiceResponse;

import java.util.List;


@RequiredArgsConstructor
public class CartProductQueryRepositoryImpl implements CartProductQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<CartDetailServiceResponse> findCartDetailResponseList(Long cartId) {
        // 342
        return null;
    }
}
