package me.jincrates.community.comment.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.community.post.domain.Post;
import me.jincrates.ecommerce.member.domain.Member;
import me.jincrates.global.common.BaseTimeEntity;

@Getter
@Entity
@org.hibernate.annotations.Comment("댓글")
@Table(name = "commetns")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    @org.hibernate.annotations.Comment("댓글 ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @org.hibernate.annotations.Comment("회원 ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    @org.hibernate.annotations.Comment("게시글 ID")
    private Post post;

    @Column(nullable = false, length = 200)
    @org.hibernate.annotations.Comment("댓글 내용")
    private String content;
}
