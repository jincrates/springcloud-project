package me.jincrates.ecommerce.cart.application.port;

import java.util.List;
import me.jincrates.ecommerce.cart.application.service.response.CartDetailServiceResponse;
import me.jincrates.ecommerce.cart.domain.Cart;
import me.jincrates.ecommerce.cart.domain.CartProduct;

public interface CartPort {

    Cart saveCart(Cart cart);

    Cart findCartByMemberId(Long memberId);

    List<CartDetailServiceResponse> findCartDetailsById(Long cartId);

    CartProduct saveCartProduct(CartProduct cartProduct);

    void deleteCartProduct(CartProduct cartProduct);

    void deleteAllCartProduct(List<CartProduct> cartProducts);

    CartProduct findCartProductById(Long cartProductId);

    CartProduct findCartProductByCartIdAndProductId(Long cartId, Long productId);
}
