package me.jincrates.community.comment.adapter.persistnece;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import me.jincrates.community.comment.application.port.CommentPort;
import me.jincrates.community.comment.domain.Comment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
class CommentAdapter implements CommentPort {

    private final CommentRepository commentRepository;

    @Override
    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> findAllCommentByPostId(Long postId) {
        return commentRepository.findAllByPostId(postId);
    }

    @Override
    public Comment findCommentByIdAndMemberIdAndPostId(Long commentId, Long memberId, Long postId) {
        return commentRepository.findByIdAndMemberIdAndPostId(commentId, memberId, postId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "댓글 정보를 찾을 수 없습니다. commentId=" + commentId + ", memberId=" + memberId + ", postId="
                                + postId));
    }

    @Override
    public void deleteComment(Comment comment) {
        commentRepository.delete(comment);
    }
}
