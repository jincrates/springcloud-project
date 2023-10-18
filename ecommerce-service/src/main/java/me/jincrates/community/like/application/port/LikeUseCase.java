package me.jincrates.community.like.application.port;

public interface LikeUseCase {

    boolean likePost(Long postId, Long memberId);
}
