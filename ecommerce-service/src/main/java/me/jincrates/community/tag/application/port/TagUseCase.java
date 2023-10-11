package me.jincrates.community.tag.application.port;

import java.util.List;
import me.jincrates.community.tag.application.service.request.TagCreateRequest;
import me.jincrates.community.tag.application.service.response.TagResponse;

public interface TagUseCase {

    TagResponse addTag(TagCreateRequest request);

    List<TagResponse> getTags();

//    void removeTag(TagDeleteRequest request);
}
