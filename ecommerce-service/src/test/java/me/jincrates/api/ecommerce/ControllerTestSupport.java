package me.jincrates.api.ecommerce;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.jincrates.api.ecommerce.products.api.controller.ProductController;
import me.jincrates.api.ecommerce.products.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = {
        // 테스트가 필요한 Controller
        ProductController.class,
})
public abstract class ControllerTestSupport {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected ProductService productService;
}
