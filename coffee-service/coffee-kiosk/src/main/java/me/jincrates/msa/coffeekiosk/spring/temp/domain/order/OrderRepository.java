package me.jincrates.msa.coffeekiosk.spring.temp.domain.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o"
            + " where o.registeredAt >= :startDateTime"
            + " and o.registeredAt < :endDateTime"
            + " and o.orderStatus = :orderStatus")
    List<Order> findOrdersBy(@Param("startDateTime") LocalDateTime startDateTime,
                             @Param("endDateTime") LocalDateTime endDateTime,
                             @Param("orderStatus") OrderStatus orderStatus);
}
