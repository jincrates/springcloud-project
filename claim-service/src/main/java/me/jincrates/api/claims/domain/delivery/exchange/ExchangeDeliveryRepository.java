package me.jincrates.api.claims.domain.delivery.exchange;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeDeliveryRepository extends JpaRepository<ExchangeDelivery, Long> {

}
