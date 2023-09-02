package me.jincrates.intg;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.jincrates.api.boilerplate.api.controller.BoilerController;
import me.jincrates.api.boilerplate.api.service.BoilerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = {
        // 테스트가 필요한 Controller
        BoilerController.class,
})
public abstract class ControllerTestSupport {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected BoilerService boilerService;
}
