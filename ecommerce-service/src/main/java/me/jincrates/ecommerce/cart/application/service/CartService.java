package me.jincrates.ecommerce.cart.application.service;

import lombok.RequiredArgsConstructor;
import me.jincrates.ecommerce.cart.application.port.CartPort;
import me.jincrates.ecommerce.cart.application.port.CartUseCase;
import me.jincrates.ecommerce.cart.application.service.request.CartCreateServiceRequest;
import me.jincrates.ecommerce.cart.application.service.request.CartProductServiceRequest;
import me.jincrates.ecommerce.cart.application.service.response.CartDetailServiceResponse;
import me.jincrates.ecommerce.cart.application.service.response.CartServiceResponse;
import me.jincrates.ecommerce.cart.domain.Cart;
import me.jincrates.ecommerce.cart.domain.CartProduct;
import me.jincrates.ecommerce.member.application.port.MemberPort;
import me.jincrates.ecommerce.member.domain.Member;
import me.jincrates.ecommerce.product.application.port.ProductPort;
import me.jincrates.ecommerce.product.domain.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartService implements CartUseCase {

    private final ProductPort productPort;
    private final CartPort cartPort;
    private final MemberPort memberPort;

    @Override
    public CartServiceResponse createCart(CartCreateServiceRequest request) {
        return null;
    }

    @Override
    public Long addCart(CartProductServiceRequest request, String email) {
        Product product = productPort.findProductById(request.getProductId());
        Member member = memberPort.findMemberByEmail(email);

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
        Member member = memberPort.findMemberByEmail(email);

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
}