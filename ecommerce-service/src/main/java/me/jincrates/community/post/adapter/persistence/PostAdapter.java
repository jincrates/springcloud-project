package me.jincrates.community.post.adapter.persistence;

import lombok.RequiredArgsConstructor;
import me.jincrates.community.post.application.port.PostPort;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostAdapter implements PostPort {

    private final PostRepository postRepository;
}
