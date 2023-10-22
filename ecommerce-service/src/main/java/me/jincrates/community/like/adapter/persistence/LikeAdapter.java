package me.jincrates.community.like.adapter.persistence;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import me.jincrates.community.like.application.port.LikePort;
import me.jincrates.community.like.domain.Like;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class LikeAdapter implements LikePort {

    private final LikeRepository likeRepository;

    @Override
    public Like saveLike(Like like) {
        return likeRepository.save(like);
    }

    @Override
    public Like findLikeByMemberIdAndPostId(Long memberId, Long postId) {
        return likeRepository.findByMemberIdAndPostId(memberId, postId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "좋아요 정보를 찾을 수 없습니다. memberId=" + memberId + ", postId=" + postId));
    }

    @Override
    public void deleteLike(Like like) {
        likeRepository.delete(like);
    }
}
