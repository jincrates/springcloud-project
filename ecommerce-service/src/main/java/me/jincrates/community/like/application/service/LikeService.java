package me.jincrates.community.like.application.service;

import lombok.RequiredArgsConstructor;
import me.jincrates.community.like.application.port.LikePort;
import me.jincrates.community.like.application.port.LikeUseCase;
import me.jincrates.community.like.domain.Like;
import me.jincrates.community.post.application.port.PostPort;
import me.jincrates.community.post.domain.Post;
import me.jincrates.ecommerce.member.application.port.MemberPort;
import me.jincrates.ecommerce.member.domain.Member;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService implements LikeUseCase {

    private final MemberPort memberPort;
    private final PostPort postPort;
    private final LikePort likePort;

    @Override
    public boolean likePost(Long postId, Long memberId) {
        Member member = memberPort.findMemberById(memberId);
        Post post = postPort.findPostById(postId);

        Like like = Like.create(member, post);
        likePort.saveLike(like);

        return true;
    }
}
