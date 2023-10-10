package me.jincrates.community.tag.adapter.persistence;

import lombok.RequiredArgsConstructor;
import me.jincrates.community.tag.application.port.TagPort;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TagAdapter implements TagPort {

    private final TagRepository tagRepository;
}
