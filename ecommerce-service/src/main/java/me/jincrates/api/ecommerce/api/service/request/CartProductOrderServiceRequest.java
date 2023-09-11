package me.jincrates.api.ecommerce.api.service.request;

import java.util.List;
import lombok.Getter;

@Getter
public class CartProductOrderServiceRequest {

    private Long cartProductId;
    private List<CartProductOrderServiceRequest> cartProductOrders;
}
