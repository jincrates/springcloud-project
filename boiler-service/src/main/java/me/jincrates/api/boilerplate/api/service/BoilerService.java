package me.jincrates.api.boilerplate.api.service;

import lombok.RequiredArgsConstructor;
import me.jincrates.api.boilerplate.api.controller.response.BoilerCreateResponse;
import me.jincrates.api.boilerplate.api.controller.response.BoilerReadResponse;
import me.jincrates.api.boilerplate.api.controller.response.BoilerUpdateResponse;
import me.jincrates.api.boilerplate.api.service.request.BoilerCreateServiceRequest;
import me.jincrates.api.boilerplate.api.service.request.BoilerUpdateServiceRequest;
import me.jincrates.api.boilerplate.api.service.response.BoilerCreateServiceResponse;
import me.jincrates.api.boilerplate.api.service.response.BoilerReadServiceResponse;
import me.jincrates.api.boilerplate.api.service.response.BoilerUpdateServiceResponse;
import me.jincrates.api.boilerplate.domain.boiler.Boiler;
import me.jincrates.api.boilerplate.domain.boiler.BoilerRepository;
import me.jincrates.api.global.core.exception.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

@Service
@RestController
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoilerService {

    private final BoilerRepository repository;

    @Transactional
    public BoilerCreateResponse create(BoilerCreateServiceRequest request) {
        Boiler boiler = Boiler.create(request.getNumber());
        return BoilerCreateServiceResponse.of(repository.save(boiler)).toResponse();
    }

    public BoilerReadResponse findById(Long id) {
        return BoilerReadServiceResponse.of(repository.findById(id)
                        .orElseThrow(() -> new BadRequestException("Boiler를 찾을 수 없습니다. id: " + id)))
                .toResponse();
    }

    @Transactional
    public BoilerUpdateResponse update(BoilerUpdateServiceRequest request) {
        Boiler boiler = repository.findById(request.getId())
                .orElseThrow(() -> new BadRequestException("Boiler를 찾을 수 없습니다. id: " + request.getId()));
        return BoilerUpdateServiceResponse.of(boiler.update(request)).toResponse();

    }
}
