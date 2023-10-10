package me.jincrates.community.tag.application.service;

import lombok.RequiredArgsConstructor;
import me.jincrates.community.tag.application.port.TagPort;
import me.jincrates.community.tag.application.port.TagUseCase;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagService implements TagUseCase {

    private final TagPort tagPort;
}
