package me.jincrates.global.common.file.adapter;

import lombok.extern.slf4j.Slf4j;
import me.jincrates.global.common.file.application.service.response.ImageResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
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

import static me.jincrates.global.common.constants.Constants.RESIZE_IMAGE_WIDTH;
import static me.jincrates.global.common.constants.Constants.ROOT_DIR;
import static me.jincrates.global.utils.DateUtils.convertFormat;

@Slf4j
@Component
public class FileHelper {

    /**
     * 임시 디렉토리를 가져옵니다.
     *
     * @param memberId
     * @param domainName
     * @return {ROOT_PATH}/{domainName}/temp/{memberId}/yyyy/MM/dd/
     */
    public String getTempDirectory(Long memberId, String domainName) {
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
    public String getDirectory(Long memberId, String domainName) {
        StringBuilder directory = new StringBuilder();
        directory.append(ROOT_DIR)
                .append(domainName).append("/")
                .append(memberId).append("/")
                .append(convertFormat(LocalDateTime.now(), "yyyy/MM/dd"))
                .append("/");
        return directory.toString();
    }

    /**
     * @param file
     * @param directory
     * @return 업로드된 이미지 원본/썸네일 경로
     */
    public ImageResponse upload(MultipartFile file, String directory) {
        String fileName = generateFileName(file);

        try {
            String originUrl = uploadOrigin(file, directory, fileName);
            String thumbUrl = uploadThumb(file, directory + "thumb/", fileName);

            return new ImageResponse(originUrl, thumbUrl);
        } catch (IOException e) {
            log.warn("file upload error :: ", e);
            return null;
        }
    }

    public List<String> moveFile(List<String> fileUrls, String targetDirectory) {
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

    public String getOriginFileName(String fileName) {
        String[] fileNameParts = fileName.split("_");
        if (fileNameParts.length < 2) {
            throw new IllegalArgumentException("Invalid file name format, fileName=" + fileName);
        }

        return fileNameParts[1];
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

    private String getMoveUrl(String fileUrl, String targetDirectory) {
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        return targetDirectory + fileName;
    }

    private String uploadOrigin(MultipartFile file, String directory, String fileName) throws IOException {
        makeDirectory(directory);
        byte[] bytes = file.getBytes();
        File localFile = new File(directory + fileName);
        Files.write(localFile.toPath(), bytes);

        return localFile.getAbsolutePath();
    }

    private String uploadThumb(MultipartFile file, String directory, String fileName) throws IOException {
        makeDirectory(directory);

        MultipartFile resizedFile = resizeFile(file, fileName, RESIZE_IMAGE_WIDTH);

        byte[] bytes = resizedFile.getBytes();
        File localFile = new File(directory + fileName);
        Files.write(localFile.toPath(), bytes);

        return localFile.getAbsolutePath();
    }

    private MultipartFile resizeFile(MultipartFile originFile, String fileName, int targetWidth) {
        try {
            BufferedImage image = ImageIO.read(originFile.getInputStream());
            int originWidth = image.getWidth();
            int originHeight = image.getHeight();

            // origin 이미지가 resizing될 사이즈보다 작을 경우 resizing 작업 안 함
            if (originWidth < targetWidth) {
                return originFile;
            }

            int targetHeight = targetWidth * originHeight / originWidth;
            BufferedImage resizedImage = resizeImage(targetWidth, image, targetHeight);

            return toMultipartFile(originFile, fileName, resizedImage);
        } catch (IOException ex) {
            log.warn("파일 resize 실패했습니다. fileName={}", fileName, ex);
            throw new RuntimeException("파일 resize 실패했습니다. fileName=" + fileName);
        }
    }

    private BufferedImage resizeImage(int targetWidth, BufferedImage image, int targetHeight) {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(image, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();

        return resizedImage;
    }

    private MockMultipartFile toMultipartFile(MultipartFile originFile, String fileName, BufferedImage resizedImage) throws IOException {
        String fileFormat = Objects.requireNonNull(originFile.getContentType()).split("/")[1];
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            ImageIO.write(resizedImage, fileFormat, outputStream);
            return new MockMultipartFile(fileName, outputStream.toByteArray());
        }
    }
}
