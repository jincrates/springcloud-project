package me.jincrates.ecommerce.carts.application.port;

import java.util.List;
import me.jincrates.ecommerce.carts.application.service.request.CartProductServiceRequest;
import me.jincrates.ecommerce.carts.application.service.response.CartDetailServiceResponse;

public interface CartUseCase {

    Long addCart(CartProductServiceRequest request, String email);

    List<CartDetailServiceResponse> getCartDetails(String email);

    void updateCartProductQuantity(Long cartProductId, int quantity);

    void deleteCartProduct(Long cartProductId);
}
