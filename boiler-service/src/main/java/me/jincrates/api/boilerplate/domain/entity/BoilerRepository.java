package me.jincrates.api.boilerplate.domain.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoilerRepository extends JpaRepository<Boiler, Long> {

}
