package me.jincrates.api.ecommerce.domain.member;


import com.querydsl.core.annotations.QueryProjection;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.api.global.common.BaseEntity;
import me.jincrates.api.global.common.enumtype.Status;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Entity
@Table(name = "MEMBER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;  // 회원 ID

    @Column(nullable = false, length = 50)
    private String name;  // 회원명

    @Column(unique = true, nullable = false)
    private String email;  // 이메일

    @Column(nullable = false)
    private String password; // 비밀번호

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;  // 권한

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;  // 상태

    @QueryProjection
    @Builder(access = AccessLevel.PRIVATE)
    private Member(String name, String email, String password, Role role, Status status) {
        if (name == null) {
            throw new IllegalArgumentException("이름은 필수입니다.");
        }
        if (email == null) {
            throw new IllegalArgumentException("이메일은 필수입니다.");
        }
        if (password == null) {
            throw new IllegalArgumentException("비밀번호는 필수입니다.");
        }

        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public static Member create(String name, String email, String password,
        PasswordEncoder passwordEncoder) {
        return Member.builder()
            .name(name)
            .email(email)
            .password(passwordEncoder.encode(password))
            .role(Role.USER)
            .status(Status.ACTIVE)
            .build();
    }
}
