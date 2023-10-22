package me.jincrates.community.post.application.port;

import me.jincrates.community.post.domain.Post;

import java.util.List;

public interface PostPort {
    void deleteAllPostInBatch();

    Post savePost(Post post);

    List<Post> findAllPost();

    Post findPostById(Long postId);
}
