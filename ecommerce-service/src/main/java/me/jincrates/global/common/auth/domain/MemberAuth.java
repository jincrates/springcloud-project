package me.jincrates.global.common.auth.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.global.common.BaseTimeEntity;
import org.hibernate.annotations.Comment;

@Getter
@Entity
@Comment("회 인증")
@Table(name = "member_auth")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberAuth extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_auth_id")
    @Comment("회원 인증 ID")
    private Long id;

    @Column(nullable = false)
    @Comment("회원 ID")
    private Long memberId;

    @Column(nullable = false)
    @Comment("refresh token")
    private String refreshToken;

    @Builder(access = AccessLevel.PRIVATE)
    private MemberAuth(Long memberId, String refreshToken) {
        this.memberId = memberId;
        this.refreshToken = refreshToken;
    }

    public static MemberAuth create(Long memberId, String refreshToken) {
        return MemberAuth.builder()
                .memberId(memberId)
                .refreshToken(refreshToken)
                .build();
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
