package me.jincrates.ecommerce.cart.adapter.persistence;

import static me.jincrates.ecommerce.cart.domain.QCartProduct.cartProduct;
import static me.jincrates.ecommerce.product.domain.QProductImage.productImage;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import me.jincrates.ecommerce.cart.application.service.response.CartDetailServiceResponse;
import me.jincrates.ecommerce.cart.application.service.response.QCartDetailServiceResponse;


@RequiredArgsConstructor
class CartProductQueryRepositoryImpl implements CartProductQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<CartDetailServiceResponse> findCartDetails(Long cartId) {
        // 342
        return queryFactory
            .select(
                new QCartDetailServiceResponse(
                    cartProduct.id,
                    cartProduct.product.productName,
                    cartProduct.product.price,
                    cartProduct.quantity,
                    productImage.imageUrl
                ))
            .from(cartProduct)
            .innerJoin(productImage).on(productImage.product.id.eq(cartProduct.product.id))
            .where(cartProduct.cart.id.eq(cartId),
                productImage.represented.eq(true))
            .orderBy(cartProduct.createdAt.desc())
            .fetch();
    }
}
