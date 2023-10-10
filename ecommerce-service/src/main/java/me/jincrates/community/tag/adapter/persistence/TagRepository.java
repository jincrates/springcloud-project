package me.jincrates.community.tag.adapter.persistence;

import java.util.Optional;
import me.jincrates.community.tag.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    boolean existsByName(String tagName);

    Optional<Tag> findByName(String name);
}
