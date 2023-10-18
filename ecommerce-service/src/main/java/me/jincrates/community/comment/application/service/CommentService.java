package me.jincrates.community.comment.application.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jincrates.community.comment.application.port.CommentPort;
import me.jincrates.community.comment.application.port.CommentUseCase;
import me.jincrates.community.comment.application.service.request.CommentCreateRequest;
import me.jincrates.community.comment.application.service.request.CommentUpdateRequest;
import me.jincrates.community.comment.application.service.response.CommentResponse;
import me.jincrates.community.comment.domain.Comment;
import me.jincrates.community.post.application.port.PostPort;
import me.jincrates.community.post.domain.Post;
import me.jincrates.ecommerce.member.application.port.MemberPort;
import me.jincrates.ecommerce.member.domain.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService implements CommentUseCase {

    private final MemberPort memberPort;
    private final PostPort postPort;
    private final CommentPort commentPort;

    @Override
    @Transactional
    public CommentResponse createComment(CommentCreateRequest request, Long memberId, Long postId) {
        Member member = memberPort.findMemberById(memberId);
        Post post = postPort.findPostById(postId);

        Comment comment = Comment.create(member, post, request.content());
        commentPort.saveComment(comment);

        post.addComment(comment);

        return new CommentResponse(
            comment.getPost().getId(),
            comment.getId(),
            comment.getContent(),
            comment.getCreatedAt(),
            comment.getUpdatedAt(),
            comment.getMember().getId(),
            comment.getMember().getName()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentResponse> getComments(Long postId) {
        List<Comment> comments = commentPort.findAllCommentByPostId(postId);

        return comments.stream().map(
            comment -> new CommentResponse(
                comment.getPost().getId(),
                comment.getId(),
                comment.getContent(),
                comment.getCreatedAt(),
                comment.getUpdatedAt(),
                comment.getMember().getId(),
                comment.getMember().getName()
            )
        ).toList();
    }

    @Override
    @Transactional
    public CommentResponse updateComment(CommentUpdateRequest request, Long memberId, Long postId,
        Long commentId) {
        Comment comment = commentPort.findCommentByIdAndMemberIdAndPostId(commentId, memberId,
            postId);
        comment.updateContent(request.content());

        return new CommentResponse(
            comment.getPost().getId(),
            comment.getId(),
            comment.getContent(),
            comment.getCreatedAt(),
            comment.getUpdatedAt(),
            comment.getMember().getId(),
            comment.getMember().getName()
        );
    }

    @Override
    public void deleteComment(Long memberId, Long postId, Long commentId) {
        Comment comment = commentPort.findCommentByIdAndMemberIdAndPostId(commentId, memberId,
            postId);
        commentPort.deleteComment(comment);
    }
}
