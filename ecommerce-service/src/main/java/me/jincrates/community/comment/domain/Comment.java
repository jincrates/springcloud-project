package me.jincrates.community.comment.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.community.post.domain.Post;
import me.jincrates.ecommerce.member.domain.Member;
import me.jincrates.global.common.BaseTimeEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@org.hibernate.annotations.Comment("댓글")
@Table(name = "comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    @org.hibernate.annotations.Comment("댓글 ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @org.hibernate.annotations.Comment("작성자 ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    @org.hibernate.annotations.Comment("게시글 ID")
    private Post post;

    @Column(nullable = false, length = 200)
    @org.hibernate.annotations.Comment("댓글 내용")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @org.hibernate.annotations.Comment("상위 댓글 ID")
    private Comment parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Comment> replies = new ArrayList<>();

    @Builder(access = AccessLevel.PRIVATE)
    private Comment(Member member, Post post, Comment parent, String content) {
        this.member = member;
        this.post = post;
        this.parent = parent;
        this.content = content;
    }

    public static Comment create(Member member, Post post, String content) {
        return Comment.builder()
                .member(member)
                .post(post)
                .content(content)
                .build();
    }

    public void updateContent(String content) {
        this.content = content;
    }
}
