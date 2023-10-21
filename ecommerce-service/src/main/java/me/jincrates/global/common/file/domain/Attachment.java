package me.jincrates.global.common.file.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.global.common.BaseTimeEntity;
import org.hibernate.annotations.Comment;

@Getter
@Entity
@Comment("첨부파일")
@Table(name = "attachments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Attachment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attachment_id")
    @Comment("첨부파일 ID")
    private Long id;

    @Column(name = "file_path", nullable = false)
    @Comment("저장된 파일 경로")
    private String filePath;

    @Column(name = "file_name", nullable = false)
    @Comment("저장된 파일명")
    private String fileName;

    @Column(name = "origin_file_name", nullable = false)
    @Comment("원본 파일명")
    private String originFileName;

    @Column(name = "file_size", nullable = false)
    @Comment("파일 크기")
    private long fileSize;

    @Column(name = "content_type", nullable = false)
    @Comment("파일의 MIME 타입")
    private String contentType;

    @Column(name = "domain_name", nullable = false)
    @Comment("파일 도메인")
    private String domainName;

    @Builder(access = AccessLevel.PRIVATE)
    private Attachment(String filePath, String fileName, String originFileName, long fileSize, String contentType, String domainName) {
        this.filePath = filePath;
        this.fileName = fileName;
        this.originFileName = originFileName;
        this.fileSize = fileSize;
        this.contentType = contentType;
        this.domainName = domainName;
    }

    public static Attachment create(String filePath, String fileName, String originFileName, long fileSize, String contentType, String domainName) {
        return Attachment.builder()
                .filePath(filePath)
                .fileName(fileName)
                .originFileName(originFileName)
                .fileSize(fileSize)
                .contentType(contentType)
                .domainName(domainName)
                .build();
    }
}
