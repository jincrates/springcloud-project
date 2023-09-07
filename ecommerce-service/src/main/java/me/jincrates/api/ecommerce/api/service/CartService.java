package me.jincrates.api.ecommerce.api.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import me.jincrates.api.ecommerce.api.service.request.CartProductServiceRequest;
import me.jincrates.api.ecommerce.domain.cart.Cart;
import me.jincrates.api.ecommerce.domain.cart.CartProduct;
import me.jincrates.api.ecommerce.domain.cart.CartProductRepository;
import me.jincrates.api.ecommerce.domain.cart.CartRepository;
import me.jincrates.api.ecommerce.domain.member.Member;
import me.jincrates.api.ecommerce.domain.member.MemberRepository;
import me.jincrates.api.ecommerce.domain.product.Product;
import me.jincrates.api.ecommerce.domain.product.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                .orElseThrow(() -> new EntityNotFoundException("상품을 찾을 수 없습니다. productId=" + request.getProductId()));
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다. email=" + email));

        Cart cart = cartRepository.findByMemberId(member.getId());
        if (cart == null) {
            cart = Cart.create(member);
            cartRepository.save(cart);
        }

        CartProduct savedCartProduct = cartProductRepository.findByCartIdAndProductId(cart.getId(), product.getId());
        if (savedCartProduct == null) {
            CartProduct cartProduct = CartProduct.create(cart, product, request.getQuantity());
            cartProductRepository.save(cartProduct);
            return cartProduct.getId();
        }

        savedCartProduct.addQuantity(request.getQuantity());
        return savedCartProduct.getId();
    }
}
