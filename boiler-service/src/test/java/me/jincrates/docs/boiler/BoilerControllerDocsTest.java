package me.jincrates.docs.boiler;

import me.jincrates.api.boilerplate.api.controller.BoilerController;
import me.jincrates.api.boilerplate.api.controller.request.BoilerCreateRequest;
import me.jincrates.api.boilerplate.api.controller.response.BoilerCreateResponse;
import me.jincrates.api.boilerplate.api.service.BoilerService;
import me.jincrates.api.boilerplate.api.service.request.BoilerCreateServiceRequest;
import me.jincrates.api.boilerplate.domain.boiler.BoilerStatus;
import me.jincrates.docs.RestDocsSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BoilerControllerDocsTest extends RestDocsSupport {

    private final BoilerService boilerService = mock(BoilerService.class);

    @Override
    protected Object initController() {
        return new BoilerController(boilerService);
    }

    @Test
    @DisplayName("신규 보일러를 등록하는 API")
    void createBoiler() throws Exception {
        BoilerCreateRequest request = new BoilerCreateRequest(10);

        given(boilerService.create(any(BoilerCreateServiceRequest.class)))
                .willReturn(BoilerCreateResponse.of(1L, BoilerStatus.NOT_STARTED, 10));

        mockMvc.perform(
                        post("/api/v1/boiler")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andDo(print())
                .andExpect(status().isOk())
                // Spring Docs
                .andDo(document("boiler-create",
                        requestFields(
                                fieldWithPath("number").type(JsonFieldType.NUMBER)
                                        .description("보일러 넘버")
                        ),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER)
                                        .description("코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),
                                fieldWithPath("data").type(JsonFieldType.OBJECT)
                                        .description("응답 데이터"),

                                // data
                                fieldWithPath("data.id").type(JsonFieldType.NUMBER)
                                        .description("보일러 ID"),
                                fieldWithPath("data.status").type(JsonFieldType.STRING)
                                        .description("보일러 상태"),
                                fieldWithPath("data.number").type(JsonFieldType.NUMBER)
                                        .description("보일러 넘버")
                        )
                ));
    }
}
