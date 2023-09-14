package me.jincrates.docs.boiler;

import me.jincrates.api.ecommerce.products.adapter.web.ProductController;
import me.jincrates.api.ecommerce.products.application.service.ProductService;
import me.jincrates.docs.RestDocsSupport;

import static org.mockito.Mockito.mock;

public class ProductControllerDocsTest extends RestDocsSupport {

    private final ProductService productService = mock(ProductService.class);

    @Override
    protected Object initController() {
        return new ProductController(productService);
    }
}
