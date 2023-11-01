package me.jincrates.global.common.file.application.port;

import me.jincrates.global.common.enumtype.FileBucket;
import me.jincrates.global.common.file.application.service.response.ImageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FilePort {

    ImageResponse uploadTempImage(MultipartFile image, Long memberId, FileBucket bucket);

    List<String> uploadImages(List<String> imageUrls, Long memberId, FileBucket bucket);

    void uploadTempVideo(MultipartFile video, Long memberId, FileBucket bucket);
}
