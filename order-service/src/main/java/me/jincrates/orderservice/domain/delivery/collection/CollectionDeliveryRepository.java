package me.jincrates.orderservice.domain.delivery.collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionDeliveryRepository extends JpaRepository<CollectionDelivery, Long> {

}
