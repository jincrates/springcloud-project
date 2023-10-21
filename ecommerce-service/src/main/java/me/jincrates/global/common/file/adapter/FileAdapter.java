package me.jincrates.global.common.file.adapter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jincrates.global.common.file.application.port.FilePort;
import me.jincrates.global.common.file.application.service.response.ImageResponse;
import me.jincrates.global.common.file.domain.Attachment;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
class FileAdapter implements FilePort {
    private final FileHelper fileHelper;
    private final FileRepository fileRepository;

    @Override
    public ImageResponse uploadTempImage(MultipartFile image, Long memberId, String domainName) {
        if (ObjectUtils.isEmpty(image)) {
            return null;
        }

        String directory = fileHelper.getTempDirectory(memberId, domainName);
        return fileHelper.upload(image, directory);
    }

    @Override
    public List<String> uploadImages(List<String> imageUrls, Long memberId, String domainName) {
        if (imageUrls.isEmpty()) {
            return null;
        }

        String directory = fileHelper.getDirectory(memberId, domainName);
        saveAttachments(imageUrls, directory, domainName);

        return fileHelper.moveFile(imageUrls, directory);
    }

    @Override
    public void uploadTempVideo(MultipartFile video, Long memberId, String domainName) {
        if (ObjectUtils.isEmpty(video)) {
            return;
        }
    }

    private void saveAttachments(List<String> fileUrls, String directory, String domainName) {
        List<Attachment> attachments = new ArrayList<>();

        try {
            for (String fileUrl : fileUrls) {
                Path path = Paths.get(fileUrl);
                Attachment attachment = createAttachment(directory, domainName, path);
                attachments.add(attachment);
            }
        } catch (IOException ex) {
            throw new RuntimeException("첨부파일 가져오기에 실패했습니다.", ex);
        }

        fileRepository.saveAll(attachments);
    }

    private Attachment createAttachment(String directory, String domainName, Path path) throws IOException {
        File file = new UrlResource((path.toUri())).getFile();

        String fileName = file.getName();
        String originFileName = fileHelper.getOriginFileName(fileName);
        long fileSize = file.length();
        String contentType = Files.probeContentType(path);

        return Attachment.create(directory, fileName, originFileName, fileSize, contentType, domainName);
    }
}
