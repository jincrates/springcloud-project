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
    private final CartProductRepository cartProductRepository;

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
        return cartProductRepository.findCartDetails(cartId);
    }

    @Override
    public CartItem saveCartProduct(CartItem cartItem) {
        return cartProductRepository.save(cartItem);
    }

    @Override
    public void deleteCartProduct(CartItem cartItem) {
        cartProductRepository.delete(cartItem);
    }

    @Override
    public void deleteAllCartProduct(List<CartItem> cartItems) {
        cartProductRepository.deleteAll(cartItems);
    }

    @Override
    public CartItem findCartProductById(Long cartProductId) {
        return cartProductRepository.findById(cartProductId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "장바구니 상품을 찾을 수 없습니다. cartProductId=" + cartProductId));
    }

    @Override
    public CartItem findCartProductByIdAndCartId(Long cartProductId, Long cartId) {
        return cartProductRepository.findByIdAndCartId(cartProductId, cartId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "장바구니 상품을 찾을 수 없습니다. cartProductId=" + cartProductId));
    }

    @Override
    public CartItem findCartProductByCartIdAndProductId(Long cartId, Long productId) {
        return cartProductRepository.findByCartIdAndProductId(cartId, productId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "장바구니 상품을 찾을 수 없습니다. productId=" + productId));
    }
}
