package me.jincrates.infra.file.adapter;

import java.io.File;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import me.jincrates.infra.file.application.FilePort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class FileAdapter implements FilePort {

    private static final String ROOT_DIR = System.getProperty("user.home") + "/uploads/";

    @Override
    public String uploadFile(MultipartFile file, String path) {
        if (ObjectUtils.isEmpty(file)) {
            return null;
        }

        try {
            String upload_path = ROOT_DIR + path;

            File uploadDir = new File(upload_path);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            byte[] bytes = file.getBytes();
            File localFile = new File(upload_path + file.getOriginalFilename());
            java.nio.file.Files.write(localFile.toPath(), bytes);

            return localFile.getAbsolutePath();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
