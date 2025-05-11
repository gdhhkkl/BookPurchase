package org.example.bookpurchase.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.bookpurchase.domain.CartList;
import org.example.bookpurchase.domain.CouPonList;
import org.example.bookpurchase.domain.Coupon;
import org.example.bookpurchase.repository.CouponListRepository;
import org.example.bookpurchase.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CouponListService {
    private final CouponRepository couponRepository;
    private final CouponListRepository couponListRepository;


    @Transactional//트랜잭션 덕분에 메소드 내부의 DB 로직은 하나의 트랜잭션으로 묶여서 처리할 수 있고요.
    public List<CouPonList> findCouponList(Long coupon){
        List<CouPonList> couPonLists = couponListRepository.findByCoupon_Id(coupon);
        return couPonLists;
    }

    public List<CouPonList> createCouponList(Coupon coupon){
        Coupon checkCoupon = couponRepository.findById(coupon.getId()).orElseThrow(()->new NullPointerException("해당 쿠폰은 없는것 같습니다."));
        List<CouPonList> couPonLists = CouPonList.createCouponList(checkCoupon);

        return   couponListRepository.saveAll(couPonLists);

    }
}
