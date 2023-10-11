package me.jincrates.infra.file.application;

import org.springframework.web.multipart.MultipartFile;

public interface FilePort {

    String uploadFile(MultipartFile file, String path);
}
