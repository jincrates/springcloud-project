package me.jincrates.orderservice.domain.orderproduct;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {

    List<OrderProduct> findAllByIdIn(List<Long> orderProductIds);
}
