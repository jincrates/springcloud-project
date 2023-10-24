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

    CartItem saveCartItem(CartItem cartItem);

    void deleteCartItem(CartItem cartItem);

    void deleteAllCartItem(List<CartItem> cartItems);

    CartItem findCartItemById(Long cartItemId);

    CartItem findCartItemByIdAndCartId(Long cartItemId, Long cartId);

    CartItem findCartItemByCartIdAndProductId(Long cartId, Long productId);

    void deleteCartItemByMemberIdAndProductIdIn(Long memberId, List<Long> productIds);
}
