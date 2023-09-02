package me.jincrates.api.boilerplate.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.jincrates.api.boilerplate.api.controller.request.BoilerCreateRequest;
import me.jincrates.api.boilerplate.api.controller.request.BoilerUpdateRequest;
import me.jincrates.api.boilerplate.api.controller.response.BoilerCreateResponse;
import me.jincrates.api.boilerplate.api.controller.response.BoilerReadResponse;
import me.jincrates.api.boilerplate.api.controller.response.BoilerUpdateResponse;
import me.jincrates.api.boilerplate.api.service.BoilerService;
import me.jincrates.api.global.common.response.CommonResponse;
import org.springframework.web.bind.annotation.*;

@Tag(name = "보일러 서비스", description = "Boiler API")
@RestController
@RequiredArgsConstructor
public class BoilerController {

    private final BoilerService service;

    @Operation(summary = "보일러 등록")
//    @Parameters({
//            @Parameter(name = "mobileUser", hidden = true, description = "authorization(암호화된 유저 아이디)", in = ParameterIn.HEADER, required = true)
//    })
    @ApiResponse(responseCode = "200", description = "보일러 등록 성공",
            content = @Content(schema = @Schema(implementation = BoilerCreateResponse.class, description = "등록된 boiler")))
    @PostMapping("/api/v1/boiler")
    public CommonResponse<BoilerCreateResponse> createBoiler(
            @Valid @RequestBody BoilerCreateRequest request) {
        return CommonResponse.ok(service.create(request.toServiceRequest()));
    }

    @Operation(summary = "보일러 조회")
    @Parameter(name = "boilerId", in = ParameterIn.PATH, required = true, example = "1")
    @ApiResponse(responseCode = "200", description = "보일러 조회 성공",
            content = @Content(schema = @Schema(implementation = BoilerCreateResponse.class, description = "등록된 boiler")))
    @GetMapping("/api/v1/boiler/{boilerId}")
    public CommonResponse<BoilerReadResponse> getBoiler(@PathVariable("boilerId") Long boilerId) {
        return CommonResponse.ok(service.findById(boilerId));
    }

    @Operation(summary = "보일러 수정")
//    @Parameter(name = "videoId", in = ParameterIn.QUERY, required = true, example = "1")
    @ApiResponse(responseCode = "200", description = "보일러 수정 성공",
            content = @Content(schema = @Schema(implementation = BoilerUpdateResponse.class, description = "수정된 boiler")))
    @PutMapping("/api/v1/boiler")
    public CommonResponse<BoilerUpdateResponse> updateBoiler(
            @Valid @RequestBody BoilerUpdateRequest request) {
        return CommonResponse.ok(service.update(request.toServiceRequest()));
    }
}
