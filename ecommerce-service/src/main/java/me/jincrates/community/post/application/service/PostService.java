package me.jincrates.community.post.application.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jincrates.community.post.application.port.PostPort;
import me.jincrates.community.post.application.port.PostUseCase;
import me.jincrates.community.post.application.service.request.PostCreateRequest;
import me.jincrates.community.post.application.service.request.PostUpdateRequest;
import me.jincrates.community.post.application.service.response.PostResponse;
import me.jincrates.community.post.domain.Post;
import me.jincrates.ecommerce.member.application.port.MemberPort;
import me.jincrates.ecommerce.member.domain.Member;
import me.jincrates.infra.file.application.FilePort;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService implements PostUseCase {

    private final MemberPort memberPort;
    private final PostPort postPort;
    private final FilePort filePort;

    @Override
    @Transactional
    public PostResponse createPost(PostCreateRequest request, Long memberId) {
        Member member = memberPort.findMemberById(memberId);

        Post post = Post.create(member, request.title(), request.content(), null);
        postPort.savePost(post);

        // $path/{memberId}/posts/{postId}
        String uploadPath = memberId + "/posts/" + post.getId() + "/";
        request.uploadFiles().forEach(file -> filePort.uploadFile(file, uploadPath));

        return new PostResponse(
            post.getId(),
            post.getTitle(),
            post.getContent(),
            post.getComments().size(),
            post.getCreatedAt(),
            post.getUpdatedAt(),
            post.getMember().getId(),
            post.getMember().getName()
        );
    }

    @Override
    public List<PostResponse> getPosts(Pageable pageable) {
        List<Post> posts = postPort.findAllPost();
        return posts.stream()
            .map(post -> new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getComments().size(),
                post.getCreatedAt(),
                post.getUpdatedAt(),
                post.getMember().getId(),
                post.getMember().getName()
            ))
            .toList();
    }

    @Override
    public PostResponse getPost(Long postId) {
        return null;
    }

    @Override
    public PostResponse updatePost(PostUpdateRequest request, Long memberId) {
        return null;
    }

    @Override
    public void deletePost(Long postId, Long memberId) {

    }
}
