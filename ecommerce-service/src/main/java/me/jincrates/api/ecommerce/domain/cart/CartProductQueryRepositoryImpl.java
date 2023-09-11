package me.jincrates.api.ecommerce.domain.cart;

import static me.jincrates.api.ecommerce.domain.cart.QCartProduct.cartProduct;
import static me.jincrates.api.ecommerce.domain.product.QProductImage.productImage;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import me.jincrates.api.ecommerce.api.service.response.CartDetailServiceResponse;
import me.jincrates.api.ecommerce.api.service.response.QCartDetailServiceResponse;


@RequiredArgsConstructor
public class CartProductQueryRepositoryImpl implements CartProductQueryRepository {

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
