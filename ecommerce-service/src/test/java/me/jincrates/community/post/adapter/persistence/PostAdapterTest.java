package me.jincrates.community.post.adapter.persistence;

import me.jincrates.IntegrationTestSupport;
import me.jincrates.community.post.application.port.PostPort;
import me.jincrates.community.post.domain.Post;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

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
        // given
        Post post = Post.create(null, "게시글 제목", "게시글 내용", null);

        // when
        Post result = postPort.savePost(post);

        // then
        assertThat(result).isNotNull()
                .extracting("productName", "price", "productDetail", "status")
                .contains("상품명", 1000, "상품 상세설명", null);
    }

}