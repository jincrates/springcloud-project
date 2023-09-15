package me.jincrates.ecommerce.cart.adapter.persistence;

import java.util.List;
import me.jincrates.ecommerce.cart.application.service.response.CartDetailServiceResponse;

interface CartProductQueryRepository {

    List<CartDetailServiceResponse> findCartDetails(Long cartId);
}
