package me.jincrates.ecommerce.cart.application.port;

import me.jincrates.ecommerce.cart.application.service.request.CartCreateRequest;
import me.jincrates.ecommerce.cart.application.service.response.CartResponse;

public interface CartUseCase {

    CartResponse createCart(CartCreateRequest request, Long memberId);

//    Long addCart(CartProductServiceRequest request, String email);
//
//    List<CartDetailServiceResponse> getCartDetails(String email);
//
//    void updateCartProductQuantity(Long cartProductId, int quantity);
//
//    void deleteCartProduct(Long cartProductId);
}
