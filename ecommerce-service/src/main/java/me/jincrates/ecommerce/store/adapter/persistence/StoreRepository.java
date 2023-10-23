package me.jincrates.ecommerce.store.adapter.persistence;

import me.jincrates.ecommerce.store.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface StoreRepository extends JpaRepository<Store, Long> {

}
