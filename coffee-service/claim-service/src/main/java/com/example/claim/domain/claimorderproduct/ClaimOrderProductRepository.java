package com.example.claim.domain.claimorderproduct;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimOrderProductRepository extends JpaRepository<ClaimOrderProduct, Long> {

}
