package me.jincrates.ecommerce.cart.application.port;

import me.jincrates.ecommerce.cart.application.service.response.CartDetailServiceResponse;
import me.jincrates.ecommerce.cart.domain.Cart;
import me.jincrates.ecommerce.cart.domain.CartItem;
import me.jincrates.ecommerce.member.domain.Member;

import java.util.List;
import java.util.Optional;

public interface CartPort {

    Cart saveCart(Cart cart);

    Optional<Cart> findCartByMember(Member member);

    Cart findCartByMemberId(Long memberId);

    List<CartDetailServiceResponse> findCartDetailsById(Long cartId);

    CartItem saveCartProduct(CartItem cartItem);

    void deleteCartProduct(CartItem cartItem);

    void deleteAllCartProduct(List<CartItem> cartItems);

    CartItem findCartProductById(Long cartProductId);

    CartItem findCartProductByIdAndCartId(Long cartProductId, Long cartId);

    CartItem findCartProductByCartIdAndProductId(Long cartId, Long productId);
}
