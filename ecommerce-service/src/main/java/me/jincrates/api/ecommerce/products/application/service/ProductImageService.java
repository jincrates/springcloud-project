package me.jincrates.api.ecommerce.products.application.service;

import lombok.RequiredArgsConstructor;
import me.jincrates.api.ecommerce.products.application.port.ProductPort;
import me.jincrates.api.ecommerce.products.domain.ProductImage;
import me.jincrates.api.global.common.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductImageService {
    private final ProductPort productPort;
    private final FileService fileService;

    public void saveProductImage(ProductImage productImage, MultipartFile image) {
        // 247
        // 파일 업로드

        // 상품 이미지 정보 저장
    }

    public void updateProductImage(Long productImageId, MultipartFile image) {
        // 259
        if (!image.isEmpty()) {
            // save
        }
        // 기존 이미지 파일 삭제

        // 파일 업로드 저장
    }
}
