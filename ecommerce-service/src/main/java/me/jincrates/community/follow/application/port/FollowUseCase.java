package me.jincrates.community.follow.application.port;

import me.jincrates.community.follow.application.service.response.FollowerResponse;
import me.jincrates.community.follow.application.service.response.FollowingResponse;

import java.util.List;

public interface FollowUseCase {

    Long followMember(Long followerId, Long followingId);

    void unfollowMember(Long followerId, Long followingId);

    List<FollowerResponse> getFollowers(Long memberId);

    List<FollowingResponse> getFollowing(Long memberId);
}
