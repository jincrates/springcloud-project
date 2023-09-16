package me.jincrates.ecommerce.member.api.controller;

import me.jincrates.ecommerce.ControllerTestSupport;

class MemberControllerTest extends ControllerTestSupport {

//    @Test
//    @WithMockUser
//    @DisplayName("신규 회원을 등록한다.")
//    void createMember() throws Exception {
//        // given
//        MemberCreateRequest request = new MemberCreateRequest("홍길동", "user@email.com",
//                "asdf1234");
//        given(memberService.register(any()))
//                .willReturn(
//                        MemberResponse.of(Member.create("홍길동", "user@email.com", "asdf1234")));
//
//        // when // then
//        mockMvc.perform(
//                        post("/api/v1/members")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(objectMapper.writeValueAsString(request))
//                                .with(csrf())
//                )
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.code").value(200))
//                .andExpect(jsonPath("$.status").value("OK"))
//                .andExpect(jsonPath("$.message").value("OK"))
//                .andExpect(jsonPath("$.data.name").value("홍길동"))
//                .andExpect(jsonPath("$.data.email").value("user@email.com"))
//                .andExpect(jsonPath("$.data.role").value("USER"))
//                .andExpect(jsonPath("$.data.status").value("ACTIVE"))
//        ;
//    }
}