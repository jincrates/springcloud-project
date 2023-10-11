package me.jincrates.infra.file.application;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface FilePort {

    String tempUploadFile(MultipartFile file, Long memberId, String domainName);

    List<String> uploadFiles(List<String> fileUrls, Long memberId, String domainName);
}
