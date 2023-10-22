package me.jincrates.community.post.application.service;

import me.jincrates.IntegrationTestSupport;
import me.jincrates.community.post.application.port.PostPort;
import me.jincrates.community.post.application.port.PostUseCase;
import me.jincrates.community.post.application.service.request.PostCreateRequest;
import me.jincrates.community.post.application.service.response.PostResponse;
import me.jincrates.ecommerce.member.application.port.MemberPort;
import me.jincrates.ecommerce.member.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PostServiceTest extends IntegrationTestSupport {

    @Autowired
    private PostUseCase postUseCase;

    @Autowired
    private PostPort postPort;

    @Autowired
    private MemberPort memberPort;

    @AfterEach
    void tearDown() {
        postPort.deleteAllPostInBatch();
        memberPort.deleteAllMemberInBatch();
    }

    @Test
    @DisplayName("신규 게시글을 생성합니다.")
    void createPost() {
        // given
        Member member = memberPort.saveMember(Member.create("홍길동", "user@email.com", "password"));
        PostCreateRequest request = new PostCreateRequest(
                "게시글 제목",
                "게시글 내용",
                List.of("imageUrl1", "imageUrl2", "imageUrl3")
        );

        // when
        PostResponse response = postUseCase.createPost(request, member.getId());

        // then
        assertThat(response).isNotNull()
                .extracting("title", "content", "likeCount", "commentCount", "imageUrls", "memberName")
                .contains("게시글 제목", "게시글 내용", 0, 0, List.of("imageUrl1", "imageUrl2", "imageUrl3"), "홍길동");
    }
}