package me.jincrates.community.post.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.community.like.domain.Like;
import me.jincrates.ecommerce.member.domain.Member;
import me.jincrates.global.common.BaseTimeEntity;
import me.jincrates.global.common.StringListConverter;
import me.jincrates.global.common.enumtype.Status;
import org.hibernate.annotations.Comment;

@Getter
@Entity
@Comment("게시글")
@Table(name = "posts")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    @Comment("게시글 ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @Comment("작성자 ID")
    private Member member;

    @Column(nullable = false, length = 200)
    @Comment("제목")
    private String title;

    @Lob
    @Column(nullable = false)
    @Comment("내용")
    private String content;

    @Column(nullable = false)
    @Comment("상태")
    private Status status;

    @Column(name = "image_urls", columnDefinition = "longtext")
    @Convert(converter = StringListConverter.class)
    @Comment("게시글 이미지")
    private List<String> imageUrls = new ArrayList<>();

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Comment("게시글 태그")
    private List<PostTag> postTags = new ArrayList<>();

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Comment("게시글 댓글")
    private List<me.jincrates.community.comment.domain.Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Comment("게시글 좋아요")
    private List<Like> likes = new ArrayList<>();

    @Builder(access = AccessLevel.PRIVATE)
    private Post(Member member, String title, String content, Status status,
        List<String> imageUrl) {
        this.member = member;
        this.title = title;
        this.content = content;
        this.status = status;
    }

    public static Post create(Member member, String title, String content, List<String> imageUrl) {
        return Post.builder()
            .member(member)
            .title(title)
            .content(content)
            .status(Status.ACTIVE)
            .imageUrl(imageUrl)
            .build();
    }

    public void addComment(me.jincrates.community.comment.domain.Comment comment) {
        this.comments.add(comment);
    }
}
