package me.jincrates.ecommerce.cart.adapter.persistence;

import me.jincrates.ecommerce.cart.application.service.response.CartDetailServiceResponse;

import java.util.List;

interface CartItemQueryRepository {

    List<CartDetailServiceResponse> findCartDetails(Long cartId);
}
