package me.jincrates.ecommerce.member.application.port;

import java.util.List;
import me.jincrates.ecommerce.member.application.service.request.MemberCreateServiceRequest;
import me.jincrates.ecommerce.member.application.service.response.MemberCreateServiceResponse;
import me.jincrates.ecommerce.member.application.service.response.MemberServiceResponse;

public interface MemberUseCase {

    MemberCreateServiceResponse register(MemberCreateServiceRequest request);

    List<MemberServiceResponse> getMembers();

    MemberServiceResponse getMemberById(Long memberId);

    MemberServiceResponse getMemberByEmail(String email);
}
