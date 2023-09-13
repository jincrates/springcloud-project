package me.jincrates.api.ecommerce.carts.domain;

import java.util.List;
import me.jincrates.api.ecommerce.carts.api.service.response.CartDetailServiceResponse;

public interface CartProductQueryRepository {

    List<CartDetailServiceResponse> findCartDetails(Long cartId);
}
