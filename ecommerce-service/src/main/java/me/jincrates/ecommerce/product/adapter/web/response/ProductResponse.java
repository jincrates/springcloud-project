package me.jincrates.ecommerce.product.adapter.web.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import me.jincrates.ecommerce.product.application.service.response.ProductImageServiceResponse;
import me.jincrates.ecommerce.product.application.service.response.ProductServiceResponse;
import me.jincrates.ecommerce.product.domain.Product;
import me.jincrates.ecommerce.product.domain.ProductImage;
import me.jincrates.ecommerce.product.domain.ProductSellingStatus;

import java.util.ArrayList;
import java.util.List;

@Schema(description = "상품 response")
@Getter
public class ProductResponse {

    @Schema(description = "상품 ID", example = "1")
    private Long id;

    @Schema(description = "상품명", example = "소크라테스의 변경")
    private String productName;

    @Schema(description = "상품 가격", example = "10000")
    private int price;

    @Schema(description = "상품 상세 설명", example = "《소크라테스의 변명》은 인류 역사상 가장 위대한 철학자인 소크라테스의 영혼의 책이다. 제자 플라톤이 스승의 위대한 사상과 진실된 인간성을 널리 알리고 영원히 기리기 위해 심혈을 기울여 쓴 〈소크라테스의 변명〉 외에 〈크리톤〉, 〈파이돈〉, 〈향연〉을 함께 엮었다.")
    private String productDetail;  // 상품 상세설명

    @Schema(description = "상품 판매상태", example = "SELLING")
    private ProductSellingStatus status;  // 상품 판매상태

    private List<ProductImageServiceResponse> productImages = new ArrayList<>();

    private List<Long> productImagesIds = new ArrayList<>();

    public ProductResponse(Long id, String productName, int price, String productDetail,
                           ProductSellingStatus status, List<ProductImageServiceResponse> productImages,
                           List<Long> productImagesIds) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.productDetail = productDetail;
        this.status = status;
        this.productImages = productImages;
        this.productImagesIds = productImagesIds;
    }

    public static ProductResponse of(Product product, List<ProductImage> productImages) {
        return new ProductResponse(
                product.getId(),
                product.getProductName(),
                product.getPrice(),
                product.getProductDetail(),
                product.getStatus(),
                productImages.stream().map(ProductImageServiceResponse::of).toList(),
                productImages.stream().map(ProductImage::getId).toList()
        );
    }

    public static ProductResponse of(ProductServiceResponse response) {
        return new ProductResponse(
                response.getId(),
                response.getProductName(),
                response.getPrice(),
                response.getProductDetail(),
                response.getStatus(),
                response.getProductImages(),
                response.getProductImagesIds()
        );
    }
}
