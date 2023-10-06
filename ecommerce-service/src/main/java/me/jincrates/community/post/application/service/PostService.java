package me.jincrates.community.post.application.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jincrates.community.post.application.port.PostPort;
import me.jincrates.community.post.application.port.PostUseCase;
import me.jincrates.community.post.application.service.request.PostCreateRequest;
import me.jincrates.community.post.application.service.request.PostUpdateRequest;
import me.jincrates.community.post.application.service.response.PostResponse;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService implements PostUseCase {

    private final PostPort postPort;

    @Override
    public Long createPost(PostCreateRequest request, Long memberId) {
        return null;
    }

    @Override
    public List<PostResponse> getPosts() {
        return null;
    }

    @Override
    public PostResponse getPost(Long postId) {
        return null;
    }

    @Override
    public void updatePost(PostUpdateRequest request, Long memberId) {

    }

    @Override
    public void deletePost(Long postId, Long memberId) {

    }
}
