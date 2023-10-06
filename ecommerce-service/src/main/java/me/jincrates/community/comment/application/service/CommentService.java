package me.jincrates.community.comment.application.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jincrates.community.comment.application.port.CommentPort;
import me.jincrates.community.comment.application.port.CommentUseCase;
import me.jincrates.community.comment.application.service.request.CommentCreateRequest;
import me.jincrates.community.comment.application.service.request.CommentUpdateRequest;
import me.jincrates.community.comment.application.service.response.CommentResponse;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService implements CommentUseCase {

    private final CommentPort commentPort;

    @Override
    public Long createComment(CommentCreateRequest request, Long memberId) {
        return null;
    }

    @Override
    public List<CommentResponse> getComments() {
        return null;
    }

    @Override
    public void updateComment(CommentUpdateRequest request, Long memberId) {

    }

    @Override
    public void deleteComment(Long commentId, Long memberId) {

    }
}
