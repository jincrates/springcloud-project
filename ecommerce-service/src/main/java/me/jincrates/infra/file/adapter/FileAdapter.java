package me.jincrates.infra.file.adapter;

import static me.jincrates.global.utils.DateUtils.convertFormat;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import me.jincrates.infra.file.application.FilePort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class FileAdapter implements FilePort {

    //TODO: file Utils로 뽑아내도 좋을 것 같다.
    private static final String ROOT_DIR = System.getProperty("user.home") + "/uploads/";

    @Override
    public String tempUploadFile(MultipartFile file, Long memberId, String domainName) {
        if (ObjectUtils.isEmpty(file)) {
            return null;
        }

        String directory = getTempDirectory(memberId, domainName);
        File uploadFile = upload(file, directory);

        assert uploadFile != null;
        return uploadFile.getAbsolutePath();
    }

    @Override
    public List<String> uploadFiles(List<String> fileUrls, Long memberId, String domainName) {
        if (fileUrls.isEmpty()) {
            return null;
        }

        String directory = getDirectory(memberId, domainName);
        return moveFile(fileUrls, directory);
    }

    /**
     * 임시 디렉토리를 가져옵니다.
     *
     * @param memberId
     * @param domainName
     * @return {ROOT_PATH}/{domainName}/temp/{memberId}/yyyy/MM/dd/
     */
    private String getTempDirectory(Long memberId, String domainName) {
        StringBuilder tempDirectory = new StringBuilder();
        tempDirectory.append(ROOT_DIR)
            .append(domainName).append("/temp/")
            .append(memberId).append("/")
            .append(convertFormat(LocalDateTime.now(), "yyyy/MM/dd"))
            .append("/");
        return tempDirectory.toString();
    }

    /**
     * 디렉토리를 가져옵니다.
     *
     * @param memberId
     * @param domainName
     * @return {ROOT_PATH}/{domainName}/{memberId}/yyyy/MM/dd/
     */
    private String getDirectory(Long memberId, String domainName) {
        StringBuilder directory = new StringBuilder();
        directory.append(ROOT_DIR)
            .append(domainName).append("/")
            .append(memberId).append("/")
            .append(convertFormat(LocalDateTime.now(), "yyyy/MM/dd"))
            .append("/");
        return directory.toString();
    }

    private File upload(MultipartFile file, String directory) {
        try {
            makeDirectory(directory);

            byte[] bytes = file.getBytes();
            File localFile = new File(directory + generateFileName(file));
            java.nio.file.Files.write(localFile.toPath(), bytes);

            return localFile;
        } catch (IOException e) {
            log.warn("file upload error :: ", e);
            return null;
        }
    }

    private void makeDirectory(String directory) {
        File fileDirectory = new File(directory);

        if (!fileDirectory.exists()) {
            fileDirectory.mkdirs();
        }
    }

    private String generateFileName(MultipartFile file) {
        String encodedFilename = Objects.requireNonNull(file.getOriginalFilename())
            .replace(" ", "_");
        return UUID.randomUUID() + "_" + encodedFilename;
    }

    private List<String> moveFile(List<String> fileUrls, String targetDirectory) {
        List<String> movedFileUrls = new ArrayList<>();
        try {
            for (String fileUrl : fileUrls) {
                makeDirectory(targetDirectory);
                String movedUrl = getMoveUrl(fileUrl, targetDirectory);
                movedFileUrls.add(movedUrl);

                Path fromPath = Paths.get(fileUrl);
                Path toPath = Paths.get(movedUrl);

                Files.move(fromPath, toPath);
            }

            return movedFileUrls;
        } catch (IOException e) {
            log.warn("file move error :: ", e);
            return null;
        }
    }

    private String getMoveUrl(String fileUrl, String targetDirectory) {
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        return targetDirectory + fileName;
    }
}
