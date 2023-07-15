package me.jincrates.msa.coffeeorder.springboot.repository.jpa;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeOrderJpaRepository extends JpaRepository<OrderJpaEntity, UUID> {

}
