package me.jincrates.docs.boiler;

import static org.mockito.Mockito.mock;

import me.jincrates.docs.RestDocsSupport;
import me.jincrates.ecommerce.product.adapter.web.ProductWebAdapter;
import me.jincrates.ecommerce.product.application.service.ProductService;

public class ProductControllerDocsTest extends RestDocsSupport {

    private final ProductService productService = mock(ProductService.class);

    @Override
    protected Object initController() {
        return new ProductWebAdapter(productService);
    }
}
