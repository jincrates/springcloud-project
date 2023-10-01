package me.jincrates.community.follow.application.service;

import me.jincrates.community.follow.application.port.FollowUseCase;
import me.jincrates.community.follow.application.service.response.FollowerResponse;
import me.jincrates.community.follow.application.service.response.FollowingResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowService implements FollowUseCase {
    @Override
    public Long followMember(Long followerId, Long followingId) {
        return null;
    }

    @Override
    public void unfollowMember(Long followerId, Long followingId) {

    }

    @Override
    public List<FollowerResponse> getFollowers(Long memberId) {
        return null;
    }

    @Override
    public List<FollowingResponse> getFollowing(Long memberId) {
        return null;
    }
}
