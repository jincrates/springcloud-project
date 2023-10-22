package me.jincrates.community.post.adapter.persistence;

import me.jincrates.community.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface PostRepository extends JpaRepository<Post, Long> {

}
