package me.jincrates.api.claims.domain.delivery.collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionDeliveryStatusRepository extends
        JpaRepository<CollectionDeliveryStatus, Long> {

}
