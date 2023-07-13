package me.jincrates.msa.coffeeorder.springboot.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeOrderJpaRepository extends JpaRepository<OrderJpaEntity, String> {

}
