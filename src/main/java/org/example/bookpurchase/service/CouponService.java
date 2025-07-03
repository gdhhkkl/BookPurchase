package org.example.bookpurchase.service;

import lombok.RequiredArgsConstructor;
import org.example.bookpurchase.domain.CouPonList;
import org.example.bookpurchase.domain.Coupon;
import org.example.bookpurchase.domain.User;
import org.example.bookpurchase.dto.UserDto;
import org.example.bookpurchase.repository.CouponListRepository;
import org.example.bookpurchase.repository.CouponRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CouponService {
    private final CouponRepository couponRepository;
    private final UserService userService;
    private final CouponListRepository couponListRepository;

    public Coupon findCouponId(Long userid){

       return couponRepository.findById(userid).orElseThrow(()-> new NullPointerException("해당 유저의 쿠폰을 못 찾겠음"));

    }
    public Coupon creatCoupon(User userID){
        User user = userService.finduser(userID.getIdentification());

         Coupon coupon = Coupon.createCoupon(user);
         Coupon coupons =couponRepository.save(coupon);
         return coupons;
    }

//    public Coupon upDateUtilization(CouPonList coupon){



    //controller에 줄 couplist를 전부 찾아 보내준다.
    //회원가잆시 자동으로 counp이 생성되고 그 아이디를 통해 couponList가 생성된다.(이때 발급일, 만료일 계산해서..이건 엔티티에서 해야하나?)
    //결제시 coupon의 id를 선택해 사용했다면 coupon의 utilization은 true로 변경되고 사용됨이라고 반환해주기
    //발급일로 부터 3일안에 사용하지 않으면 만료일지남 반환하고
}
