package me.jincrates.ecommerce.member.application.port;

import me.jincrates.ecommerce.member.application.service.request.MemberCreateRequest;
import me.jincrates.ecommerce.member.application.service.request.MemberLoginRequest;
import me.jincrates.ecommerce.member.application.service.response.MemberResponse;

import java.util.List;

public interface MemberUseCase {

    MemberResponse register(MemberCreateRequest request);

    MemberResponse login(MemberLoginRequest request);

    List<MemberResponse> getMembers();

    MemberResponse getMemberById(Long memberId);

    //회원 탈퇴(비활성화)

    //회원 완전 삭제
}
