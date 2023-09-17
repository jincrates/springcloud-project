package me.jincrates.ecommerce.product.application.service;

import lombok.RequiredArgsConstructor;
import me.jincrates.ecommerce.product.application.port.ProductPort;
import me.jincrates.ecommerce.product.domain.ProductImage;
import me.jincrates.infra.file.application.FilePort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductImageService {

    private final ProductPort productPort;
    private final FilePort filePort;

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
