package me.jincrates.community.tag.application.port;

import java.util.List;
import java.util.Optional;
import me.jincrates.community.tag.domain.Tag;

public interface TagPort {

    boolean existsByTagName(String tagName);

    Optional<Tag> findByTagName(String tagName);

    Tag saveTag(Tag tag);

    List<Tag> findAllTag();

//    void deleteTag(Tag tag);
}
