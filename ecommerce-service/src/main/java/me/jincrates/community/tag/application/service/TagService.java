package me.jincrates.community.tag.application.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import me.jincrates.community.tag.application.port.TagPort;
import me.jincrates.community.tag.application.port.TagUseCase;
import me.jincrates.community.tag.application.service.request.TagCreateRequest;
import me.jincrates.community.tag.application.service.response.TagResponse;
import me.jincrates.community.tag.domain.Tag;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagService implements TagUseCase {

    private final TagPort tagPort;

    @Override
    public TagResponse addTag(TagCreateRequest request) {
        Optional<Tag> tag = tagPort.findByTagName(request.tagName());

        if (tag.isEmpty()) {
            Tag savedTag = tagPort.saveTag(Tag.createTag(request.tagName()));
            return new TagResponse(savedTag.getId(), savedTag.getName());
        }

        return new TagResponse(tag.get().getId(), tag.get().getName());
    }

    @Override
    public List<TagResponse> getTags() {
        List<Tag> tags = tagPort.findAllTag();
        return tags.stream()
            .map(t -> new TagResponse(t.getId(), t.getName()))
            .toList();
    }

//    @Override
//    public void removeTag(TagDeleteRequest request) {
//        Tag tag = tagPort.findByTagName(request.tagName()).orElseThrow(
//            () -> new EntityNotFoundException("삭제할 엔티티를 찾을 수 없습니다. name=" + request.tagName()));
//        tagPort.deleteTag(tag);
//    }
}
