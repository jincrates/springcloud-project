package me.jincrates.api.ecommerce.carts.adapter.database;

import java.util.List;
import me.jincrates.api.ecommerce.carts.application.service.response.CartDetailServiceResponse;

interface CartProductQueryRepository {

    List<CartDetailServiceResponse> findCartDetails(Long cartId);
}
