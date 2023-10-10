package me.jincrates.community.post.application.port;

import java.util.List;
import me.jincrates.community.post.application.service.request.PostCreateRequest;
import me.jincrates.community.post.application.service.request.PostUpdateRequest;
import me.jincrates.community.post.application.service.response.PostResponse;

public interface PostUseCase {

    // 게시글 작성
    PostResponse createPost(PostCreateRequest request, Long memberId);

    // 게시글 목록 조회
    List<PostResponse> getPosts();

    // 게시글 상세 조회
    PostResponse getPost(Long postId);

    // 게시글 수정
    PostResponse updatePost(PostUpdateRequest request, Long memberId);

    // 게시글 삭제
    void deletePost(Long postId, Long memberId);
}
