package org.example.bookpurchase.repository;

import org.example.bookpurchase.domain.CouPonList;
import org.example.bookpurchase.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponListRepository extends JpaRepository<CouPonList, Long> {
    @Query("select m from CouPonList m where m.coupon.id = :coupon_id")
    List<CouPonList> findByCoupon(Long coupon_id);

    CouPonList findByCouponType(String couponType);

    @Query("select m from CouPonList  m where m.id = :couponId")
    CouPonList findByCouponListId(Long couponId);


}