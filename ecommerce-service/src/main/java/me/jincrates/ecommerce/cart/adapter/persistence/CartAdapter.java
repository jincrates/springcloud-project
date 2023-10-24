package me.jincrates.ecommerce.cart.adapter.persistence;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import me.jincrates.ecommerce.cart.application.port.CartPort;
import me.jincrates.ecommerce.cart.application.service.response.CartDetailServiceResponse;
import me.jincrates.ecommerce.cart.domain.Cart;
import me.jincrates.ecommerce.cart.domain.CartItem;
import me.jincrates.ecommerce.member.domain.Member;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
class CartAdapter implements CartPort {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Optional<Cart> findCartByMember(Member member) {
        return cartRepository.findByMember(member);
    }

    @Override
    public Cart findCartByMemberId(Long memberId) {
        return cartRepository.findByMemberId(memberId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "장바구니를 찾을 수 없습니다. memberId=" + memberId));
    }

    @Override
    public List<CartDetailServiceResponse> findCartDetailsById(Long cartId) {
        return cartItemRepository.findCartDetails(cartId);
    }

    @Override
    public CartItem saveCartItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    @Override
    public void deleteCartItem(CartItem cartItem) {
        cartItemRepository.delete(cartItem);
    }

    @Override
    public void deleteAllCartItem(List<CartItem> cartItems) {
        cartItemRepository.deleteAll(cartItems);
    }

    @Override
    public CartItem findCartItemById(Long cartItemId) {
        return cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "장바구니 상품을 찾을 수 없습니다. cartItemId=" + cartItemId));
    }

    @Override
    public CartItem findCartItemByIdAndCartId(Long cartItemId, Long cartId) {
        return cartItemRepository.findByIdAndCartId(cartItemId, cartId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "장바구니 상품을 찾을 수 없습니다. cartItemId=" + cartItemId));
    }

    @Override
    public CartItem findCartItemByCartIdAndProductId(Long cartId, Long productId) {
        return cartItemRepository.findByCartIdAndProductId(cartId, productId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "장바구니 상품을 찾을 수 없습니다. productId=" + productId));
    }

    @Override
    public void deleteCartItemByMemberIdAndProductIdIn(Long memberId, List<Long> productIds) {
        Cart cart = findCartByMemberId(memberId);
        cartItemRepository.deleteByCartIdAndProductIdIn(cart.getId(), productIds);
    }
}
