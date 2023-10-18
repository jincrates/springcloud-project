package me.jincrates.community.like.adapter.persistence;

import lombok.RequiredArgsConstructor;
import me.jincrates.community.like.application.port.LikePort;
import me.jincrates.community.like.domain.Like;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LikeAdapter implements LikePort {

    private final LikeRepository likeRepository;

    @Override
    public Like saveLike(Like like) {
        return likeRepository.save(like);
    }
}
