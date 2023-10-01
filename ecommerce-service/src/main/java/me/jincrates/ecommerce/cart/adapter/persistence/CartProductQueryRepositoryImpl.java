package me.jincrates.ecommerce.cart.adapter.persistence;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import me.jincrates.ecommerce.cart.application.service.response.CartDetailServiceResponse;
import me.jincrates.ecommerce.cart.application.service.response.QCartDetailServiceResponse;

import java.util.List;

import static me.jincrates.ecommerce.cart.domain.QCartItem.cartItem;
import static me.jincrates.ecommerce.product.domain.QProductImage.productImage;


@RequiredArgsConstructor
class CartProductQueryRepositoryImpl implements CartProductQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<CartDetailServiceResponse> findCartDetails(Long cartId) {
        // 342
        return queryFactory
                .select(
                        new QCartDetailServiceResponse(
                                cartItem.id,
                                cartItem.product.name,
                                cartItem.product.price,
                                cartItem.quantity,
                                productImage.imageUrl
                        ))
                .from(cartItem)
                .innerJoin(productImage).on(productImage.product.id.eq(cartItem.product.id))
                .where(cartItem.cart.id.eq(cartId),
                        productImage.represented.eq(true))
                .orderBy(cartItem.createdAt.desc())
                .fetch();
    }
}
