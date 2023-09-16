package me.jincrates.ecommerce.member.application.port;

import me.jincrates.ecommerce.member.application.service.request.MemberCreateServiceRequest;
import me.jincrates.ecommerce.member.application.service.request.MemberLoginServiceRequest;
import me.jincrates.ecommerce.member.application.service.response.MemberCreateServiceResponse;
import me.jincrates.ecommerce.member.application.service.response.MemberServiceResponse;

import java.util.List;

public interface MemberUseCase {

    MemberCreateServiceResponse register(MemberCreateServiceRequest request);

    MemberServiceResponse login(MemberLoginServiceRequest request);

    List<MemberServiceResponse> getMembers();

    MemberServiceResponse getMemberById(Long memberId);

    MemberServiceResponse getMemberByEmail(String email);
}
