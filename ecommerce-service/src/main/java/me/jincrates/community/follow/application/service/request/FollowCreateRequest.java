package me.jincrates.community.follow.application.service.request;

import jakarta.validation.constraints.NotNull;

public record FollowCreateRequest(
        @NotNull(message = "팔로워 받는 사용자는 필수입니다.")
        Long followingId
) {
}
