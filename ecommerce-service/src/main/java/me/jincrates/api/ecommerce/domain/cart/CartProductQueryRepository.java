package me.jincrates.api.ecommerce.domain.cart;

import java.util.List;
import me.jincrates.api.ecommerce.api.service.response.CartDetailServiceResponse;

public interface CartProductQueryRepository {

    List<CartDetailServiceResponse> findCartDetails(Long cartId);
}
