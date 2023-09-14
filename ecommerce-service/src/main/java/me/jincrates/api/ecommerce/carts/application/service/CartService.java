package me.jincrates.api.ecommerce.carts.application.service;

import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import me.jincrates.api.ecommerce.carts.adapter.web.usecase.CartUseCase;
import me.jincrates.api.ecommerce.carts.application.port.CartPort;
import me.jincrates.api.ecommerce.carts.application.service.request.CartProductServiceRequest;
import me.jincrates.api.ecommerce.carts.application.service.response.CartDetailServiceResponse;
import me.jincrates.api.ecommerce.carts.domain.Cart;
import me.jincrates.api.ecommerce.carts.domain.CartProduct;
import me.jincrates.api.ecommerce.members.domain.Member;
import me.jincrates.api.ecommerce.members.domain.MemberRepository;
import me.jincrates.api.ecommerce.products.domain.product.Product;
import me.jincrates.api.ecommerce.products.domain.product.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartService implements CartUseCase {

    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    private final CartPort cartPort;

    @Override
    public Long addCart(CartProductServiceRequest request, String email) {
        Product product = productRepository.findById(request.getProductId())
            .orElseThrow(() -> new EntityNotFoundException(
                "상품을 찾을 수 없습니다. productId=" + request.getProductId()));
        Member member = getMemberByEmail(email);

        Cart cart = cartPort.findCartByMemberId(member.getId());
        if (cart == null) {
            cart = Cart.create(member, null);
            cartPort.saveCart(cart);
        }

        CartProduct savedCartProduct = cartPort.findCartProductByCartIdAndProductId(cart.getId(),
            product.getId());
        if (savedCartProduct == null) {
            CartProduct cartProduct = CartProduct.create(cart, product, request.getQuantity());
            cartPort.saveCartProduct(cartProduct);
            return cartProduct.getId();
        }

        savedCartProduct.addQuantity(request.getQuantity());
        return savedCartProduct.getId();
    }

    @Override
    public List<CartDetailServiceResponse> getCartDetails(String email) {
        Member member = getMemberByEmail(email);

        Cart cart = cartPort.findCartByMemberId(member.getId());
        if (cart == null) {
            return new ArrayList<>();
        }

        return cartPort.findCartDetailsById(cart.getId());
    }

    @Override
    public void updateCartProductQuantity(Long cartProductId, int quantity) {
        // TODO: 유저 인증 추가
        CartProduct cartProduct = cartPort.findCartProductById(cartProductId);
        cartProduct.updateQuantity(quantity);
    }

    @Override
    public void deleteCartProduct(Long cartProductId) {
        // TODO: 유저 인증 추가
        CartProduct cartProduct = cartPort.findCartProductById(cartProductId);
        cartPort.deleteCartProduct(cartProduct);
    }

    private Member getMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
            .orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다. email=" + email));
    }
}
