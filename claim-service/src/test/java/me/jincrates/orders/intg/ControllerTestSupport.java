package me.jincrates.orders.intg;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.jincrates.api.claims.api.controller.ClaimController;
import me.jincrates.api.claims.api.service.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = {
        ClaimController.class
})
public abstract class ControllerTestSupport {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected ClaimService claimService;
}
