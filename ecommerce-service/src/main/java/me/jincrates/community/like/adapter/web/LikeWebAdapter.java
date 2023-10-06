package me.jincrates.community.like.adapter.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import me.jincrates.community.like.application.port.LikeUseCase;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "좋아요 서비스", description = "좋아요 API")
@RestController
@RequiredArgsConstructor
public class LikeWebAdapter {

    private final LikeUseCase likeUseCase;


}
