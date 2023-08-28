package me.jincrates.claimservice.domain.order.orderproduct;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {

    List<OrderProduct> findAllByIdIn(List<Long> orderProductIds);
}
