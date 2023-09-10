package me.jincrates.api.ecommerce.domain.cart;

import me.jincrates.api.ecommerce.api.service.response.CartDetailServiceResponse;

import java.util.List;

public interface CartQueryRepository {

    List<CartDetailServiceResponse> findCartDetailResponseList(Long cartId);
}
