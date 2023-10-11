package me.jincrates.community.post.application.port;

import java.util.List;
import me.jincrates.community.post.domain.Post;

public interface PostPort {

    Post savePost(Post post);

    List<Post> findAllPost();

    Post findPostById(Long postId);
}
