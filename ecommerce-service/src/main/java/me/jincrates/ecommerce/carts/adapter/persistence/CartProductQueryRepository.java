package me.jincrates.ecommerce.carts.adapter.persistence;

import java.util.List;
import me.jincrates.ecommerce.carts.application.service.response.CartDetailServiceResponse;

interface CartProductQueryRepository {

    List<CartDetailServiceResponse> findCartDetails(Long cartId);
}
