package me.jincrates.ecommerce.product.adapter.web.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.ecommerce.product.application.service.request.ProductCreateServiceRequest;

@Schema(description = "상품 등록 request")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductCreateRequest {

    @Schema(description = "상품명", example = "소크라테스의 변경")
    @NotBlank(message = "상품명은 필수입니다.")
    private String productName;  // 상품명

    @Schema(description = "상품 가격", example = "10000")
    @Min(value = 0, message = "상품 가격은 0원 이상이어야 합니다.")
    @NotNull(message = "상품 가격은 필수입니다.")
    private int price;

    @Schema(description = "상품 상세 설명", example = "《소크라테스의 변명》은 인류 역사상 가장 위대한 철학자인 소크라테스의 영혼의 책이다. 제자 플라톤이 스승의 위대한 사상과 진실된 인간성을 널리 알리고 영원히 기리기 위해 심혈을 기울여 쓴 〈소크라테스의 변명〉 외에 〈크리톤〉, 〈파이돈〉, 〈향연〉을 함께 엮었다.")
    @NotBlank(message = "상품 상세 설명은 필수입니다.")
    private String productDetail;  // 상품 상세 설명

    @Schema(description = "재고 수량", example = "100")
    @Min(value = 0, message = "재고 수량 0개 이상이어야 합니다.")
    @NotNull(message = "재고 수량은 필수입니다.")
    private int quantity; // 재고 수량

    public ProductCreateRequest(String productName, int price, String productDetail, int quantity) {
        this.productName = productName;
        this.price = price;
        this.productDetail = productDetail;
        this.quantity = quantity;
    }

    public ProductCreateServiceRequest toServiceRequest() {
        return ProductCreateServiceRequest.of(this);
    }
}
