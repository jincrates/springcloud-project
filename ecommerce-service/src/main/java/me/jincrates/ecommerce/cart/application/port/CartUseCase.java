package me.jincrates.ecommerce.cart.application.port;

import me.jincrates.ecommerce.cart.application.service.request.CartCreateServiceRequest;
import me.jincrates.ecommerce.cart.application.service.response.CartServiceResponse;

public interface CartUseCase {

    CartServiceResponse createCart(CartCreateServiceRequest request);

//    Long addCart(CartProductServiceRequest request, String email);
//
//    List<CartDetailServiceResponse> getCartDetails(String email);
//
//    void updateCartProductQuantity(Long cartProductId, int quantity);
//
//    void deleteCartProduct(Long cartProductId);
}
