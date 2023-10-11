package me.jincrates.ecommerce.member.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.global.common.BaseTimeEntity;
import me.jincrates.global.common.enumtype.Status;
import org.hibernate.annotations.Comment;
import org.springframework.util.Assert;

@Getter
@Entity
@Comment("회원")
@Table(name = "members")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    @Comment("회원 ID")
    private Long id;

    @Column(nullable = false, length = 50)
    @Comment("이름")
    private String name;

    @Column(unique = true, nullable = false)
    @Comment("이메일")
    private String email;

    @Column(nullable = false)
    @Comment("비밀번호")
    private String password;

    @Column(length = 100)
    @Comment("한줄소개")
    private String bio;

    @Column()
    @Comment("이미지 url")
    private String imageUrl;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Comment("권한")
    private Role role;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Comment("상태")
    private Status status;

    @Builder(access = AccessLevel.PRIVATE)
    private Member(String name, String email, String password, String bio, String imageUrl, Role role, Status status) {
        Assert.notNull(name, "이름은 필수입니다.");
        Assert.notNull(email, "이메일은 필수입니다.");
        Assert.notNull(password, "비밀번호는 필수입니다.");
        Assert.notNull(role, "권한은 필수입니다.");
        Assert.notNull(status, "상태는 필수입니다.");

        this.name = name;
        this.email = email;
        this.password = password;
        this.bio = bio;
        this.imageUrl = imageUrl;
        this.role = role;
        this.status = status;
    }

    public static Member create(String name, String email, String password) {
        return Member.builder()
                .name(name)
                .email(email)
                .password(password)
                .role(Role.USER)
                .status(Status.ACTIVE)
                .build();
    }
}
