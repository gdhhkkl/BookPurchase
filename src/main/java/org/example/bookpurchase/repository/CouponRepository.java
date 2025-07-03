package org.example.bookpurchase.repository;


import org.example.bookpurchase.domain.Cart;
import org.example.bookpurchase.domain.CouPonList;
import org.example.bookpurchase.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
//    @Query("select m from Coupon  m where m.id = :couponId")
//    Optional<Coupon> findById (Long couponId);

}
