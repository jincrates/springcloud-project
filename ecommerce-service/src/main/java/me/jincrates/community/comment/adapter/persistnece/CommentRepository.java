package me.jincrates.community.comment.adapter.persistnece;

import me.jincrates.community.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByPostId(Long postId);

    Optional<Comment> findByIdAndMemberIdAndPostId(Long commentId, Long memberId, Long postId);
}
