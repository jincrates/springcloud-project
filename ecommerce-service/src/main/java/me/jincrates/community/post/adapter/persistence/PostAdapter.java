package me.jincrates.community.post.adapter.persistence;

import java.util.List;
import lombok.RequiredArgsConstructor;
import me.jincrates.community.post.application.port.PostPort;
import me.jincrates.community.post.domain.Post;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostAdapter implements PostPort {

    private final PostRepository postRepository;

    @Override
    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public List<Post> findAllPost() {
        return postRepository.findAll();
    }
}
