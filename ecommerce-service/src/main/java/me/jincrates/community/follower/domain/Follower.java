package me.jincrates.community.follower.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.ecommerce.member.domain.Member;
import me.jincrates.global.common.BaseTimeEntity;
import org.hibernate.annotations.Comment;

@Getter
@Entity
@Comment("팔로워")
@Table(name = "followers")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Follower extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "follower_id")
    @Comment("팔로워 ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_id", nullable = false)
    @Comment("팔로우 하는 사용자")
    private Member follower;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "following_id", nullable = false)
    @Comment("팔로우 받는 사용자")
    private Member following;
}
