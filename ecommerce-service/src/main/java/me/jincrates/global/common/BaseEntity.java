package me.jincrates.global.common;

import jakarta.persistence.*;
import lombok.Getter;
import me.jincrates.global.common.enumtype.Status;
import org.hibernate.annotations.Comment;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity extends BaseTimeEntity {

//    @CreatedBy
//    @Column(name = "created_by", updatable = false, nullable = false)
//    private Long createdBy;
//
//    @LastModifiedBy
//    @Column(name = "updated_by")
//    private Long updatedBy;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Comment("상태")
    private Status status = Status.ACTIVE;

    public void setInactive() {
        this.status = Status.INACTIVE;
    }
}
