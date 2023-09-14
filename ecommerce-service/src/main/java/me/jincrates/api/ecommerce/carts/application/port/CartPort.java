package me.jincrates.api.ecommerce.carts.application.port;

import java.util.List;
import me.jincrates.api.ecommerce.carts.application.service.response.CartDetailServiceResponse;
import me.jincrates.api.ecommerce.carts.domain.Cart;
import me.jincrates.api.ecommerce.carts.domain.CartProduct;

public interface CartPort {

    Cart saveCart(Cart cart);

    Cart findCartByMemberId(Long memberId);

    List<CartDetailServiceResponse> findCartDetailsById(Long cartId);

    CartProduct saveCartProduct(CartProduct cartProduct);

    void deleteCartProduct(CartProduct cartProduct);

    CartProduct findCartProductById(Long cartProductId);

    CartProduct findCartProductByCartIdAndProductId(Long cartId, Long productId);
}
