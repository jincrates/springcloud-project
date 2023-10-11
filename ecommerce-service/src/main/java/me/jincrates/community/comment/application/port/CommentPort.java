package me.jincrates.community.comment.application.port;

import java.util.List;
import me.jincrates.community.comment.domain.Comment;

public interface CommentPort {

    Comment saveComment(Comment comment);

    List<Comment> findAllCommentByPostId(Long postId);
}
