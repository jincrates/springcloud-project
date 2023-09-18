package me.jincrates.ecommerce;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.jincrates.ecommerce.auth.JwtProvider;
import me.jincrates.ecommerce.member.adapter.web.MemberWebAdapter;
import me.jincrates.ecommerce.member.application.port.MemberUseCase;
import me.jincrates.ecommerce.product.adapter.web.ProductWebAdapter;
import me.jincrates.ecommerce.product.application.port.ProductUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = {
    // 테스트가 필요한 Controller
    MemberWebAdapter.class,
    ProductWebAdapter.class,
})
public abstract class ControllerTestSupport {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected JwtProvider jwtProvider;

    @MockBean
    protected MemberUseCase memberUseCase;

    @MockBean
    protected ProductUseCase productUseCase;
}
