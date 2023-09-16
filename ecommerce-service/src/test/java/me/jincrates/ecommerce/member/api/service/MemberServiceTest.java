package me.jincrates.ecommerce.member.api.service;

import me.jincrates.ecommerce.IntegrationTestSupport;

class MemberServiceTest extends IntegrationTestSupport {
//
//    @Autowired
//    private MemberService memberService;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//
//    @Autowired
//    private MemberRepository memberRepository;
//
//    @AfterEach
//    void tearDown() {
//        memberRepository.deleteAllInBatch();
//    }
//
//    @Test
//    @DisplayName("신규 회원을 등록합니다.")
//    void createMember() {
//        // given
//        MemberCreateRequest request = new MemberCreateRequest("회원명",
//                "user@email.com", "password");
//
//        // when
//        MemberResponse result = memberService.register(request);
//
//        // then
//        assertThat(result).isNotNull()
//                .extracting("name", "email", "role", "status")
//                .contains("회원명", "user@email.com", Role.USER, Status.ACTIVE);
//    }
//
//    @Test
//    @DisplayName("신규 회원을 등록할 때, 이미 가입한 이메일은 가입할 수 없습니다.")
//    void createMemberWithDuplicateEmail() {
//        // given
//        MemberCreateRequest member = new MemberCreateRequest("회원명2",
//                "user@email.com", "password1");
//        memberService.register(member);
//
//        MemberCreateRequest request = new MemberCreateRequest("회원명2",
//                "user@email.com", "password2");
//
//        // when // then
//        assertThatThrownBy(() -> memberService.register(request))
//                .isInstanceOf(IllegalArgumentException.class)
//                .hasMessage("이미 가입한 회원입니다.");
//    }
}