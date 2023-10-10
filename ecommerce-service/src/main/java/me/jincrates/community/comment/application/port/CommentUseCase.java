package me.jincrates.community.comment.application.port;

import java.util.List;
import me.jincrates.community.comment.application.service.request.CommentCreateRequest;
import me.jincrates.community.comment.application.service.request.CommentUpdateRequest;
import me.jincrates.community.comment.application.service.response.CommentResponse;

public interface CommentUseCase {

    CommentResponse createComment(CommentCreateRequest request, Long memberId);

    List<CommentResponse> getComments();

    CommentResponse updateComment(CommentUpdateRequest request, Long memberId);

    void deleteComment(Long commentId, Long memberId);
}
