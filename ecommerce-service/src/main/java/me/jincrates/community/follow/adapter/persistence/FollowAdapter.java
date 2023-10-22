package me.jincrates.community.follow.adapter.persistence;


import lombok.RequiredArgsConstructor;
import me.jincrates.community.follow.application.port.FollowPort;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class FollowAdapter implements FollowPort {

    private final FollowRepository followRepository;
}
