package me.jincrates.ecommerce.cart.adapter.persistence;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import me.jincrates.ecommerce.cart.application.service.response.CartDetailServiceResponse;

import java.util.List;


@RequiredArgsConstructor
class CartItemQueryRepositoryImpl implements CartItemQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<CartDetailServiceResponse> findCartDetails(Long cartId) {
//        // 342
//        return queryFactory
//                .select(
//                        new QCartDetailServiceResponse(
//                                cartItem.id,
//                                cartItem.product.name,
//                                cartItem.product.price,
//                                cartItem.quantity,
//                        ))
//                .from(cartItem)
//                .where(cartItem.cart.id.eq(cartId),
//                .orderBy(cartItem.createdAt.desc())
//                .fetch();
        return null;
    }
}
