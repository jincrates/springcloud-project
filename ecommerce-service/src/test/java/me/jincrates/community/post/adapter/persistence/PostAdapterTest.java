package me.jincrates.community.post.adapter.persistence;

import me.jincrates.IntegrationTestSupport;
import me.jincrates.community.post.application.port.PostPort;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class PostAdapterTest extends IntegrationTestSupport {

    @Autowired
    private PostPort postPort;

    @Autowired
    private PostRepository postRepository;

    @AfterEach
    void tearDown() {
        postRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("신규 게시글을 등록한다.")
    void savePost() {

    }
}