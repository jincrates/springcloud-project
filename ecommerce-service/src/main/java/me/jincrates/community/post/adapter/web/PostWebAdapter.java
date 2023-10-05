package me.jincrates.community.post.adapter.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import me.jincrates.community.post.application.port.PostUseCase;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "게시글 서비스", description = "게시글 등록/조회/삭제 API")
@RestController
@RequiredArgsConstructor
public class PostWebAdapter {

    private final PostUseCase postUseCase;
}
