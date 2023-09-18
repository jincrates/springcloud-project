package me.jincrates.ecommerce.cart.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jincrates.ecommerce.cart.application.port.CartPort;
import me.jincrates.ecommerce.cart.application.port.CartUseCase;
import me.jincrates.ecommerce.cart.application.service.request.CartCreateRequest;
import me.jincrates.ecommerce.cart.application.service.request.CartProductDeleteRequest;
import me.jincrates.ecommerce.cart.application.service.request.CartProductRequest;
import me.jincrates.ecommerce.cart.application.service.request.CartProductUpdateRequest;
import me.jincrates.ecommerce.cart.application.service.response.CartResponse;
import me.jincrates.ecommerce.cart.domain.Cart;
import me.jincrates.ecommerce.cart.domain.CartProduct;
import me.jincrates.ecommerce.member.application.port.MemberPort;
import me.jincrates.ecommerce.member.domain.Member;
import me.jincrates.ecommerce.product.application.port.ProductPort;
import me.jincrates.ecommerce.product.domain.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartService implements CartUseCase {

    private final ProductPort productPort;
    private final CartPort cartPort;
    private final MemberPort memberPort;

    @Override
    @Transactional
    public CartResponse createCart(CartCreateRequest request, Long memberId) {
        Member member = memberPort.findMemberById(memberId);
        Cart cart = cartPort.findCartByMember(member)
            .orElse(new Cart(member));

        // TODO: 벌크로 변경 필요
        for (CartProductRequest each : request.cartProducts()) {
            Product product = productPort.findProductById(each.productId());
            cart.addCartProduct(product, each.quantity());
        }

        Cart savedCart = cartPort.saveCart(cart);

        return CartResponse.of(savedCart);
    }

    @Override
    public CartResponse getMyCart(Long memberId) {
        Member member = memberPort.findMemberById(memberId);
        Cart cart = cartPort.findCartByMember(member)
            .orElse(new Cart(member));

        return CartResponse.of(cart);
    }

    @Override
    @Transactional
    public Long updateCartProductQuantity(CartProductUpdateRequest request, Long memberId) {
        CartProduct cartProduct = cartPort.findCartProductById(request.cartProductId());

        if (!memberId.equals(cartProduct.getCart().getMember().getId())) {
            log.warn("invalid access cart, cartId={}, memberId={}",
                cartProduct.getCart().getId(),
                memberId);
            throw new IllegalArgumentException("잘못된 접근입니다.");
        }

        cartProduct.updateQuantity(request.quantity());
        return cartProduct.getId();
    }

    @Override
    @Transactional
    public void deleteCartProduct(CartProductDeleteRequest request, Long memberId) {
        Cart cart = cartPort.findCartByMemberId(memberId);
        CartProduct cartProduct = cartPort.findCartProductByIdAndCartId(request.cartProductId(),
            cart.getId());
        cartPort.deleteCartProduct(cartProduct);
    }

//    @Override
//    public Long addCart(CartProductServiceRequest request, String email) {
//        Product product = productPort.findProductById(request.getProductId());
//        Member member = memberPort.findMemberByEmail(email);
//
//        Cart cart = cartPort.findCartByMemberId(member.getId()).get();
//        if (cart == null) {
//            cart = Cart.create(member, null);
//            cartPort.saveCart(cart);
//        }
//
//        CartProduct savedCartProduct = cartPort.findCartProductByCartIdAndProductId(cart.getId(),
//                product.getId());
//        if (savedCartProduct == null) {
//            CartProduct cartProduct = CartProduct.create(cart, product, request.getQuantity());
//            cartPort.saveCartProduct(cartProduct);
//            return cartProduct.getId();
//        }
//
//        savedCartProduct.addQuantity(request.getQuantity());
//        return savedCartProduct.getId();
//    }
//
//    @Override
//    public List<CartDetailServiceResponse> getCartDetails(String email) {
//        Member member = memberPort.findMemberByEmail(email);
//
//        Cart cart = cartPort.findCartByMemberId(member.getId()).get();
//        if (cart == null) {
//            return new ArrayList<>();
//        }
//
//        return cartPort.findCartDetailsById(cart.getId());
//    }
//

//
//    @Override
//    public void deleteCartProduct(Long cartProductId) {
//        // TODO: 유저 인증 추가
//        CartProduct cartProduct = cartPort.findCartProductById(cartProductId);
//        cartPort.deleteCartProduct(cartProduct);
//    }
}
