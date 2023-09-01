package me.jincrates.api.boilerplate.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.jincrates.api.boilerplate.api.controller.request.BoilerCreateRequest;
import me.jincrates.api.boilerplate.api.controller.request.BoilerUpdateRequest;
import me.jincrates.api.boilerplate.api.controller.response.BoilerCreateResponse;
import me.jincrates.api.boilerplate.api.controller.response.BoilerUpdateResponse;
import me.jincrates.api.boilerplate.api.service.BoilerService;
import me.jincrates.api.global.common.response.CommonResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoilerController {

    private final BoilerService service;

    @PostMapping("api/v1/entities")
    public CommonResponse<BoilerCreateResponse> createEntity(
        @Valid @RequestBody BoilerCreateRequest request) {
        return CommonResponse.ok(service.create(request.toServiceRequest()));
    }

    @PutMapping("api/v1/entities")
    public CommonResponse<BoilerUpdateResponse> updateEntity(
        @Valid @RequestBody BoilerUpdateRequest request) {
        return CommonResponse.ok(service.update(request.toServiceRequest()));
    }
}
