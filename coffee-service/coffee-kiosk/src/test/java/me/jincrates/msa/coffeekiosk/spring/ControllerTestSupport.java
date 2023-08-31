package me.jincrates.msa.coffeekiosk.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.jincrates.msa.coffeekiosk.spring.temp.api.controller.order.OrderController;
import me.jincrates.msa.coffeekiosk.spring.temp.api.controller.product.ProductController;
import me.jincrates.msa.coffeekiosk.spring.temp.api.service.order.OrderService;
import me.jincrates.msa.coffeekiosk.spring.temp.api.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = {
        OrderController.class,
        ProductController.class
})
public abstract class ControllerTestSupport {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected OrderService orderService;

    @MockBean
    protected ProductService productService;
}
