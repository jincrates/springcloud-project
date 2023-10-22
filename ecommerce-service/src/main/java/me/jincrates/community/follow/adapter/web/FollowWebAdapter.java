package me.jincrates.community.follow.adapter.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import me.jincrates.community.follow.application.port.FollowUseCase;
import me.jincrates.community.follow.application.service.response.FollowerResponse;
import me.jincrates.community.follow.application.service.response.FollowingResponse;
import me.jincrates.global.common.auth.JwtProvider;
import me.jincrates.global.common.response.CommonResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "팔로우 서비스", description = "팔로우 신청/조회/삭제 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/follows")
public class FollowWebAdapter {

    private final JwtProvider jwtProvider;
    private final FollowUseCase followUseCase;

    @Operation(summary = "팔로우 신청")
    @Parameter(name = HttpHeaders.AUTHORIZATION, hidden = true, description = "JWT Token", in = ParameterIn.HEADER, required = true)
    @PostMapping("/{memberId}")
    public CommonResponse<Long> followMember(
            @PathVariable("memberId") Long followingId,
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization
    ) {
        Long loginMemberId = jwtProvider.parseToken(authorization.substring(7));

        return CommonResponse.created(followUseCase.followMember(loginMemberId, followingId));
    }

    @Operation(summary = "팔로우 취소")
    @Parameter(name = HttpHeaders.AUTHORIZATION, hidden = true, description = "JWT Token", in = ParameterIn.HEADER, required = true)
    @DeleteMapping("/{memberId}")
    public CommonResponse<Void> unfollowMember(
            @PathVariable("memberId") Long followingId,
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization
    ) {
        Long loginMemberId = jwtProvider.parseToken(authorization.substring(7));
        followUseCase.unfollowMember(loginMemberId, followingId);

        return CommonResponse.noContent();
    }

    @Operation(summary = "특정 사용자의 팔로워 목록 조회")
    @GetMapping("/{memberId}/followers")
    public CommonResponse<List<FollowerResponse>> getFollowers(
            @PathVariable Long memberId
    ) {
        List<FollowerResponse> response = followUseCase.getFollowers(memberId);

        return CommonResponse.ok(response);
    }

    @Operation(summary = "특정 사용자가 팔로우하는 사람 조회")
    @GetMapping("/{memberId}/following")
    public CommonResponse<List<FollowingResponse>> getFollowing(
            @PathVariable Long memberId
    ) {
        List<FollowingResponse> response = followUseCase.getFollowing(memberId);

        return CommonResponse.ok(response);
    }
}
