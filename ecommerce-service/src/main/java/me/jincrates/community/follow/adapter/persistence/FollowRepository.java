package me.jincrates.community.follow.adapter.persistence;

import me.jincrates.community.follow.domain.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface FollowRepository extends JpaRepository<Follow, Long> {
}
