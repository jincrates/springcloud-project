package me.jincrates.ecommerce.carts.adapter.persistence;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import me.jincrates.ecommerce.carts.application.port.CartPort;
import me.jincrates.ecommerce.carts.application.service.response.CartDetailServiceResponse;
import me.jincrates.ecommerce.carts.domain.Cart;
import me.jincrates.ecommerce.carts.domain.CartProduct;
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
    public Cart findCartByMemberId(Long memberId) {
        return cartRepository.findByMemberId(memberId);
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
                "장바구님 상품을 찾을 수 없습니다. cartProductId=" + cartProductId));
    }

    @Override
    public CartProduct findCartProductByCartIdAndProductId(Long cartId, Long productId) {
        return cartProductRepository.findByCartIdAndProductId(cartId, productId);
    }
}
