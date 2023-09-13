package me.jincrates.api.ecommerce.products.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import me.jincrates.api.ecommerce.products.api.service.ProductService;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "상품 서비스", description = "상품 등록/수정/조회 API")
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

//    @Operation(summary = "보일러 등록")
//    @ApiResponse(responseCode = "200", description = "보일러 등록 성공",
//            content = @Content(schema = @Schema(implementation = BoilerCreateResponse.class, description = "등록된 boiler")))
//    @PostMapping("/api/v1/boiler")
//    public CommonResponse<BoilerCreateResponse> createBoiler(
//            @Valid @RequestBody BoilerCreateRequest request) {
//        return CommonResponse.ok(service.create(request.toServiceRequest()));
//    }
//
//    @Operation(summary = "보일러 조회")
//    @Parameter(name = "boilerId", in = ParameterIn.PATH, required = true, example = "1")
//    @ApiResponse(responseCode = "200", description = "보일러 조회 성공",
//            content = @Content(schema = @Schema(implementation = BoilerCreateResponse.class, description = "등록된 boiler")))
//    @GetMapping("/api/v1/boiler/{boilerId}")
//    public CommonResponse<BoilerReadResponse> getBoiler(@PathVariable("boilerId") Long boilerId) {
//        return CommonResponse.ok(service.findById(boilerId));
//    }
}
