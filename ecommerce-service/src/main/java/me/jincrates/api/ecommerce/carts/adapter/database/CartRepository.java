package me.jincrates.api.ecommerce.carts.adapter.database;

import me.jincrates.api.ecommerce.carts.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// TODO: 포트 어댑터로 바꾸면 default 접근 제한자로 수정
@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findByMemberId(Long memberId);
}
