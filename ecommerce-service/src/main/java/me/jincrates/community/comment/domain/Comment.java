package me.jincrates.community.comment.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.community.post.domain.Post;
import me.jincrates.ecommerce.member.domain.Member;
import me.jincrates.global.common.BaseTimeEntity;

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
    @JoinColumn(name = "post_id")
    @org.hibernate.annotations.Comment("게시글 ID")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @org.hibernate.annotations.Comment("작성자 ID")
    private Member member;

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
    public Comment(Post post, Member member, Comment parent, String content) {
        this.post = post;
        this.member = member;
        this.parent = parent;
        this.content = content;
    }

    public static Comment create(Post post, Member member, String content) {
        return Comment.builder()
            .post(post)
            .member(member)
            .content(content)
            .build();
    }

    public void updateContent(String content) {
        this.content = content;
    }
}
