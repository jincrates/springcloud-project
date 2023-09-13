package me.jincrates.api.ecommerce.carts.api.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import me.jincrates.api.ecommerce.carts.api.service.request.CartProductServiceRequest;
import me.jincrates.api.ecommerce.carts.api.service.response.CartDetailServiceResponse;
import me.jincrates.api.ecommerce.carts.domain.Cart;
import me.jincrates.api.ecommerce.carts.domain.CartProduct;
import me.jincrates.api.ecommerce.carts.domain.CartProductRepository;
import me.jincrates.api.ecommerce.carts.domain.CartRepository;
import me.jincrates.api.ecommerce.members.domain.Member;
import me.jincrates.api.ecommerce.members.domain.MemberRepository;
import me.jincrates.api.ecommerce.products.domain.product.Product;
import me.jincrates.api.ecommerce.products.domain.product.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartService {

    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final CartProductRepository cartProductRepository;

    @Transactional
    public Long addCart(CartProductServiceRequest request, String email) {
        Product product = productRepository.findById(request.getProductId())
            .orElseThrow(() -> new EntityNotFoundException(
                "상품을 찾을 수 없습니다. productId=" + request.getProductId()));
        Member member = getMemberByEmail(email);

        Cart cart = cartRepository.findByMemberId(member.getId());
        if (cart == null) {
            cart = Cart.create(member, null);
            cartRepository.save(cart);
        }

        CartProduct savedCartProduct = cartProductRepository.findByCartIdAndProductId(cart.getId(),
            product.getId());
        if (savedCartProduct == null) {
            CartProduct cartProduct = CartProduct.create(cart, product, request.getQuantity());
            cartProductRepository.save(cartProduct);
            return cartProduct.getId();
        }

        savedCartProduct.addQuantity(request.getQuantity());
        return savedCartProduct.getId();
    }

    public List<CartDetailServiceResponse> getCartDetails(String email) {
        Member member = getMemberByEmail(email);

        Cart cart = cartRepository.findByMemberId(member.getId());
        if (cart == null) {
            return new ArrayList<>();
        }

        return cartProductRepository.findCartDetails(cart.getId());
    }

    @Transactional
    public void updateCartProductQuantity(Long cartProductId, int quantity) {
        // TODO: 유저 인증 추가
        CartProduct cartProduct = getCartProductById(cartProductId);
        cartProduct.updateQuantity(quantity);
    }

    @Transactional
    public void deleteCartProduct(Long cartProductId) {
        // TODO: 유저 인증 추가
        CartProduct cartProduct = getCartProductById(cartProductId);
        cartProductRepository.delete(cartProduct);
    }

    private Member getMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
            .orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다. email=" + email));
    }

    private CartProduct getCartProductById(Long cartProductId) {
        return cartProductRepository.findById(cartProductId)
            .orElseThrow(() -> new EntityNotFoundException(
                "장바구님 상품을 찾을 수 없습니다. cartProductId=" + cartProductId));
    }
}
