package org.example.bookpurchase.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "couponId")
    private Long id;

    @ColumnDefault("false")//@Buider.Defaulf->초기화
    private boolean utilization;

    @ManyToOne
    private User user;

    private void setUtilization(boolean utilization){
        this.utilization =utilization;
    }

    public void setUSer(User user){
        this.user =user;
    }

    public static Coupon createCoupon(User user){

        Coupon coupon = new Coupon();
        coupon.setUSer(user);
        return coupon;
    }

//    public boolean useThat(){
//        if(this.)
//    }//utilization을 true로 바꾸러면 어떻게 함수 짜야함?




}
