package me.jincrates.community.tag.adapter.persistence;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import me.jincrates.community.tag.application.port.TagPort;
import me.jincrates.community.tag.domain.Tag;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TagAdapter implements TagPort {

    private final TagRepository tagRepository;


    @Override
    public boolean existsByTagName(String tagName) {
        return tagRepository.existsByName(tagName);
    }

    @Override
    public Optional<Tag> findByTagName(String tagName) {
        return tagRepository.findByName(tagName);
    }

    @Override
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public List<Tag> findAllTag() {
        return tagRepository.findAll();
    }

//    @Override
//    public void deleteTag(Tag tag) {
//        tagRepository.delete(tag);
//    }
}
