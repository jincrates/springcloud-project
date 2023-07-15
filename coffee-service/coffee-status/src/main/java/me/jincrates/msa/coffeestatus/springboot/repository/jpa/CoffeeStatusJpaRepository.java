package me.jincrates.msa.coffeestatus.springboot.repository.jpa;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeStatusJpaRepository extends JpaRepository<OrderStatusJpaEntity, UUID> {

}
