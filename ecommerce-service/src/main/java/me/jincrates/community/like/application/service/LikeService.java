package me.jincrates.community.like.application.service;

import lombok.RequiredArgsConstructor;
import me.jincrates.community.like.application.port.LikePort;
import me.jincrates.community.like.application.port.LikeUseCase;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService implements LikeUseCase {

    private final LikePort likePort;
}
