package org.example.bookpurchase.domain;


import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Getter
@Entity
public class CouPonList {
    private static final Logger log = LoggerFactory.getLogger(CouPonList.class);
    @Id
    @Column(name = "couponNumber")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String couponType;

    @ColumnDefault("false")//@Buider.Defaulf->초기화//사용유무
    private boolean utilization;

    private LocalDateTime issueDate;
    @Column(nullable = true)
    private LocalDateTime useDate;

    private LocalDateTime expiryDate;

    @ManyToOne
    @JoinColumn
    private Coupon coupon;



    private void setIssueDate(LocalDateTime issueDate){
        this.issueDate =issueDate;
    }
    private void setExpiryDate(LocalDateTime expiryDate){
        this.expiryDate = expiryDate;
    }
    private void setCouponType(String couponType){
        this.couponType =couponType;
    }
    private void setCoupon(Coupon coupon){
        this.coupon = coupon;
    }
    private void setUseDate(LocalDateTime useDate){
        this.useDate= useDate;//null이다가 쿠폰 사용시 현재 요일 뜨기
    }

    public void setUtilization(boolean utilization){
        this.utilization =utilization;
    }



    public static List<CouPonList> createCouponList(Coupon coupon){
        LocalDateTime now = LocalDateTime.now();
        String[] type = new String[2];
        type[0] = "10%할인";
        type[1] = "100원할인";

        List<CouPonList> couPonLists = new ArrayList<>();
        for(int i=0; i<type.length; i++) {

            CouPonList couPonList = new CouPonList();
            couPonList.setCoupon(coupon);
            couPonList.setCouponType(type[i]);
            couPonList.setIssueDate(now);//발급일
            couPonList.setExpiryDate(now.plusWeeks(5));//만료일
            couPonList.setUseDate(null);
            couPonLists.add(couPonList);
        }
        return  couPonLists;
        
    }
    public void useCoupon(){
        this.useDate = LocalDateTime.now();

    }




}
