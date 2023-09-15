package me.jincrates.ecommerce.member.api.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import me.jincrates.ecommerce.ControllerTestSupport;
import me.jincrates.ecommerce.member.adapter.web.request.MemberCreateRequest;
import me.jincrates.ecommerce.member.application.service.response.MemberCreateServiceResponse;
import me.jincrates.ecommerce.member.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

class MemberControllerTest extends ControllerTestSupport {

    @Test
    @WithMockUser
    @DisplayName("신규 회원을 등록한다.")
    void createMember() throws Exception {
        // given
        MemberCreateRequest request = MemberCreateRequest.create("홍길동", "user@email.com",
            "asdf1234");
        given(memberService.register(any()))
            .willReturn(
                MemberCreateServiceResponse.of(Member.create("홍길동", "user@email.com", "asdf1234")));

        // when // then
        mockMvc.perform(
                post("/api/v1/members")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request))
                    .with(csrf())
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value(200))
            .andExpect(jsonPath("$.status").value("OK"))
            .andExpect(jsonPath("$.message").value("OK"))
            .andExpect(jsonPath("$.data.name").value("홍길동"))
            .andExpect(jsonPath("$.data.email").value("user@email.com"))
            .andExpect(jsonPath("$.data.role").value("USER"))
            .andExpect(jsonPath("$.data.status").value("ACTIVE"))
        ;
    }
}