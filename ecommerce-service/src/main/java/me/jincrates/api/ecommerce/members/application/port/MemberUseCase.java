package me.jincrates.api.ecommerce.members.application.port;

import java.util.List;
import me.jincrates.api.ecommerce.members.application.service.request.MemberCreateServiceRequest;
import me.jincrates.api.ecommerce.members.application.service.response.MemberCreateServiceResponse;
import me.jincrates.api.ecommerce.members.application.service.response.MemberServiceResponse;

public interface MemberUseCase {

    MemberCreateServiceResponse register(MemberCreateServiceRequest request);

    List<MemberServiceResponse> getMembers();

    MemberServiceResponse getMemberById(Long memberId);

    MemberServiceResponse getMemberByEmail(String email);
}
