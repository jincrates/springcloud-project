package me.jincrates.claimservice.domain.claim.deliveryFee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimDeliveryFeeRepository extends JpaRepository<ClaimDeliveryFee, Long> {

}
