package me.jincrates.ecommerce.cart.adapter.persistence;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import me.jincrates.ecommerce.cart.application.port.CartPort;
import me.jincrates.ecommerce.cart.application.service.response.CartDetailServiceResponse;
import me.jincrates.ecommerce.cart.domain.Cart;
import me.jincrates.ecommerce.cart.domain.CartProduct;
import me.jincrates.ecommerce.member.domain.Member;
import org.springframework.stereotype.Component;

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
    public CartProduct saveCartProduct(CartProduct cartProduct) {
        return cartProductRepository.save(cartProduct);
    }

    @Override
    public void deleteCartProduct(CartProduct cartProduct) {
        cartProductRepository.delete(cartProduct);
    }

    @Override
    public void deleteAllCartProduct(List<CartProduct> cartProducts) {
        cartProductRepository.deleteAll(cartProducts);
    }

    @Override
    public CartProduct findCartProductById(Long cartProductId) {
        return cartProductRepository.findById(cartProductId)
            .orElseThrow(() -> new EntityNotFoundException(
                "장바구니 상품을 찾을 수 없습니다. cartProductId=" + cartProductId));
    }

    @Override
    public CartProduct findCartProductByIdAndCartId(Long cartProductId, Long cartId) {
        return cartProductRepository.findByIdAndCartId(cartProductId, cartId)
            .orElseThrow(() -> new EntityNotFoundException(
                "장바구니 상품을 찾을 수 없습니다. cartProductId=" + cartProductId));
    }

    @Override
    public CartProduct findCartProductByCartIdAndProductId(Long cartId, Long productId) {
        return cartProductRepository.findByCartIdAndProductId(cartId, productId)
            .orElseThrow(() -> new EntityNotFoundException(
                "장바구니 상품을 찾을 수 없습니다. productId=" + productId));
    }
}
