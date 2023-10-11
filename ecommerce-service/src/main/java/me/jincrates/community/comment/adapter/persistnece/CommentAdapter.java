package me.jincrates.community.comment.adapter.persistnece;

import java.util.List;
import lombok.RequiredArgsConstructor;
import me.jincrates.community.comment.application.port.CommentPort;
import me.jincrates.community.comment.domain.Comment;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentAdapter implements CommentPort {

    private final CommentRepository commentRepository;

    @Override
    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> findAllCommentByPostId(Long postId) {
        return commentRepository.findAllByPostId(postId);
    }
}
