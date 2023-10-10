package me.jincrates.community.tag.application.port;

import me.jincrates.community.tag.application.service.request.TagCreateRequest;
import me.jincrates.community.tag.application.service.response.TagResponse;

public interface TagUseCase {

    TagResponse addTag(TagCreateRequest request);

//    void removeTag(TagDeleteRequest request);
}
