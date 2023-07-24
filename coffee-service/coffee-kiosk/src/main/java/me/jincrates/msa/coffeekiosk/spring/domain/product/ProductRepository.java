package me.jincrates.msa.coffeekiosk.spring.domain.product;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * select *
     * <p>
     * from product
     * <p>
     * where selling_status in ('SELLING', 'HOLD');
     */
    List<Product> findAllBySellingStatusIn(List<ProductSellingStatus> sellingStatuses);

    /**
     * select *
     * <p>
     * from product
     * <p>
     * where product_number in ('001', '002');
     */
    List<Product> findAllByProductNumberIn(List<String> productNumbers);

    @Query(value = "select p.product_number from Product p order by id desc limit 1", nativeQuery = true)
    String findLatestProductNumber();

    Optional<Product> findTop1ByOrderByIdDesc();

    Optional<Product> findFirstByOrderByIdDesc();
}
