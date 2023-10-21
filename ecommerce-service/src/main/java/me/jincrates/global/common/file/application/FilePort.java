package me.jincrates.global.common.file.application;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FilePort {

    ImageResponse uploadTempImage(MultipartFile image, Long memberId, String domainName);

    List<String> uploadImages(List<String> imageUrls, Long memberId, String domainName);
}
