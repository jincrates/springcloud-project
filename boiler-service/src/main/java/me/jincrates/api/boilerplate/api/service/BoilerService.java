package me.jincrates.api.boilerplate.api.service;

import lombok.RequiredArgsConstructor;
import me.jincrates.api.boilerplate.api.controller.response.BoilerCreateResponse;
import me.jincrates.api.boilerplate.api.controller.response.BoilerReadResponse;
import me.jincrates.api.boilerplate.api.controller.response.BoilerUpdateResponse;
import me.jincrates.api.boilerplate.api.service.request.BoilerCreateServiceRequest;
import me.jincrates.api.boilerplate.api.service.request.BoilerUpdateServiceRequest;
import me.jincrates.api.boilerplate.domain.entity.Boiler;
import me.jincrates.api.boilerplate.domain.entity.BoilerRepository;
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
        return BoilerCreateResponse.of(repository.save(boiler));
    }

    public BoilerReadResponse findById(Long id) {
        return BoilerReadResponse.of(repository.findById(id)
                .orElseThrow(() -> new BadRequestException("Boiler를 찾을 수 없습니다. id: " + id)));
    }

    @Transactional
    public BoilerUpdateResponse update(BoilerUpdateServiceRequest request) {
        Boiler boiler = repository.findById(request.getId())
                .orElseThrow(() -> new BadRequestException("Boiler를 찾을 수 없습니다. id: " + request.getId()));
        return BoilerUpdateResponse.of(boiler.update(request));

    }
}
