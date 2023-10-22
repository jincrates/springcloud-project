package me.jincrates.community.post.adapter.persistence;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import me.jincrates.community.post.application.port.PostPort;
import me.jincrates.community.post.domain.Post;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
class PostAdapter implements PostPort {

    private final PostRepository postRepository;

    @Override
    public void deleteAllPostInBatch() {
        postRepository.deleteAllInBatch();
    }

    @Override
    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public List<Post> findAllPost() {
        return postRepository.findAll();
    }

    @Override
    public Post findPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다. postId=" + postId));
    }
}
