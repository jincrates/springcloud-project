package me.jincrates.orderservice.domain.claim.history;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimHistoryRepository extends JpaRepository<ClaimHistory, Long> {

}
