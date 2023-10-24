package me.jincrates.ecommerce.cart.application.port;

import me.jincrates.ecommerce.cart.application.service.request.CartCreateRequest;
import me.jincrates.ecommerce.cart.application.service.request.CartProductDeleteRequest;
import me.jincrates.ecommerce.cart.application.service.request.CartProductUpdateRequest;
import me.jincrates.ecommerce.cart.application.service.response.CartProductResponse;
import me.jincrates.ecommerce.cart.application.service.response.CartResponse;

public interface CartUseCase {

    CartResponse createCart(CartCreateRequest request, Long memberId);

    CartResponse getCart(Long memberId);

    CartProductResponse updateCartProductQuantity(CartProductUpdateRequest request, Long memberId);

    void deleteCartProduct(CartProductDeleteRequest request, Long memberId);
}
