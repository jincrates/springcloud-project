package me.jincrates.ecommerce.member.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.global.common.BaseEntity;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Comment;
import org.springframework.util.Assert;

@Getter
@Entity
@Comment("회원")
@Table(name = "members")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

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

    @Builder(access = AccessLevel.PRIVATE)
    private Member(String name, String email, String password, String bio, String imageUrl, Role role) {
        Assert.notNull(name, "이름은 필수입니다.");
        Assert.notNull(email, "이메일은 필수입니다.");
        Assert.notNull(password, "비밀번호는 필수입니다.");
        Assert.notNull(role, "권한은 필수입니다.");

        this.name = name;
        this.email = email;
        this.password = password;
        this.bio = bio;
        this.imageUrl = imageUrl;
        this.role = role;
    }

    public static Member create(String name, String email, String password) {
        return Member.builder()
                .name(name)
                .email(email)
                .password(password)
                .role(Role.USER)
                .build();
    }

    public void update(String name, String password, String bio, String imageUrl) {
        if (!StringUtils.isBlank(name)) {
            setName(name);
        }
        if (!StringUtils.isBlank(password)) {
            setPassword(password);
        }
        if (!StringUtils.isBlank(bio)) {
            setBio(bio);
        }
        if (!StringUtils.isBlank(imageUrl)) {
            setImageUrl(imageUrl);
        }
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    private void setBio(String bio) {
        this.bio = bio;
    }

    private void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
