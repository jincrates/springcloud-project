package me.jincrates.api.ecommerce.api.service;

import lombok.RequiredArgsConstructor;
import me.jincrates.api.ecommerce.api.service.request.ProductCreateServiceRequest;
import me.jincrates.api.ecommerce.domain.product.Product;
import me.jincrates.api.ecommerce.domain.product.ProductImage;
import me.jincrates.api.ecommerce.domain.product.ProductImageRepository;
import me.jincrates.api.ecommerce.domain.product.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RestController
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductImageService productImageService;
    private final ProductImageRepository productImageRepository;

    @Transactional
    public Long createProduct(ProductCreateServiceRequest request, List<MultipartFile> images) {
        //TODO: 재고 등록 필요
        // 상품 등록
        Product product = Product.create(request.getProductName(), request.getPrice(), request.getProductDetail());
        productRepository.save(product);

        // 이미지 등록
        for (int i = 0; i < images.size(); i++) {
            ProductImage productImage = new ProductImage(product);

            if(i == 0) {
                productImage.represented();
            }
            productImageService.saveProductImage(productImage, images.get(i));
        }

        return product.getId();
    }
}
