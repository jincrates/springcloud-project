package me.jincrates.ecommerce.member.application.port;

import me.jincrates.ecommerce.member.application.service.request.MemberCreateRequest;
import me.jincrates.ecommerce.member.application.service.request.MemberLoginRequest;
import me.jincrates.ecommerce.member.application.service.request.MemberUpdateRequest;
import me.jincrates.ecommerce.member.application.service.response.MemberResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberUseCase {

    MemberResponse register(MemberCreateRequest request);

    MemberResponse login(MemberLoginRequest request);

    List<MemberResponse> getMembers(Pageable pageable);

    MemberResponse getMemberById(Long memberId);

    MemberResponse updateMember(MemberUpdateRequest request, Long memberId);

    MemberResponse makeMemberInactive(Long memberId);
}
