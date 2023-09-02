package me.jincrates.api.ecommerce.api.service;

import lombok.RequiredArgsConstructor;
import me.jincrates.api.ecommerce.domain.product.ProductImage;
import me.jincrates.api.ecommerce.domain.product.ProductImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductImageService {
    private final ProductImageRepository productImageRepository;
    private final FileService fileService;

    public void saveProductImage(ProductImage productImage, MultipartFile image) {
        // 247
        // 파일 업로드

        // 상품 이미지 정보 저장
    }
}
