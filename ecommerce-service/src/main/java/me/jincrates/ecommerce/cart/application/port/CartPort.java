package me.jincrates.ecommerce.cart.application.port;

import java.util.List;
import java.util.Optional;
import me.jincrates.ecommerce.cart.application.service.response.CartDetailServiceResponse;
import me.jincrates.ecommerce.cart.domain.Cart;
import me.jincrates.ecommerce.cart.domain.CartProduct;
import me.jincrates.ecommerce.member.domain.Member;

public interface CartPort {

    Cart saveCart(Cart cart);

    Optional<Cart> findCartByMember(Member member);

    Cart findCartByMemberId(Long memberId);

    List<CartDetailServiceResponse> findCartDetailsById(Long cartId);

    CartProduct saveCartProduct(CartProduct cartProduct);

    void deleteCartProduct(CartProduct cartProduct);

    void deleteAllCartProduct(List<CartProduct> cartProducts);

    CartProduct findCartProductById(Long cartProductId);

    CartProduct findCartProductByIdAndCartId(Long cartProductId, Long cartId);

    CartProduct findCartProductByCartIdAndProductId(Long cartId, Long productId);
}
