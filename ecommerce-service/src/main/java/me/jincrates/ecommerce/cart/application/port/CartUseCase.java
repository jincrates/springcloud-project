package me.jincrates.ecommerce.cart.application.port;

import me.jincrates.ecommerce.cart.application.service.request.CartCreateRequest;
import me.jincrates.ecommerce.cart.application.service.request.CartProductDeleteRequest;
import me.jincrates.ecommerce.cart.application.service.request.CartProductUpdateRequest;
import me.jincrates.ecommerce.cart.application.service.response.CartResponse;

public interface CartUseCase {

    CartResponse createCart(CartCreateRequest request, Long memberId);

    CartResponse getMyCart(Long memberId);

    Long updateCartProductQuantity(CartProductUpdateRequest request, Long memberId);

    void deleteCartProduct(CartProductDeleteRequest request, Long memberId);

//    Long addCart(CartProductServiceRequest request, String email);
//
//    List<CartDetailServiceResponse> getCartDetails(String email);
//
//    void updateCartProductQuantity(Long cartProductId, int quantity);
//
//    void deleteCartProduct(Long cartProductId);
}
