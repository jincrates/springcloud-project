package me.jincrates.community.post.application.service;

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
import me.jincrates.global.common.file.application.port.FilePort;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        Post post = Post.create(member, request.title(), request.content(), request.imageUrls());

        postPort.savePost(post);

//        List<String> uploadFileUrls = new ArrayList<>();
//        request.uploadFiles()
//                .forEach(file -> uploadFileUrls.add(filePort.uploadTempFile(file, memberId, "posts")));
//
//        filePort.uploadFiles(uploadFileUrls, memberId, "posts");

        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getLikes().size(),
                post.getComments().size(),
                post.getImageUrls(),
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
                        post.getLikes().size(),
                        post.getComments().size(),
                        post.getImageUrls(),
                        post.getCreatedAt(),
                        post.getUpdatedAt(),
                        post.getMember().getId(),
                        post.getMember().getName()
                ))
                .toList();
    }

    @Override
    public PostResponse getPost(Long postId) {
        Post post = postPort.findPostById(postId);
        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getLikes().size(),
                post.getComments().size(),
                post.getImageUrls(),
                post.getCreatedAt(),
                post.getUpdatedAt(),
                post.getMember().getId(),
                post.getMember().getName()
        );
    }

    @Override
    public PostResponse updatePost(PostUpdateRequest request, Long memberId) {
        return null;
    }

    @Override
    public void deletePost(Long postId, Long memberId) {

    }
}
