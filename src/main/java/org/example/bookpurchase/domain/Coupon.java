package org.example.bookpurchase.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Coupon {
    @Id
    @Column(nullable = false, name = "couponId")
    private Long id;
    /*
    * 10%,100원 할인권이라는 두 종료의 쿠폰을 받을수 있음
    * 회원은 본인에게 발급된 쿠폰 목록을 볼 수 있으며, 도서를 구매할 때, 사용하지 않은 유효 쿠폰(종료일이남아있는 쿠폰)을 사용하여 구매비용 일부를 할인받을 수있음
    * 사용한 쿠폰을 사용여부 및 사용일 속성값이 변경되고, 쿠폰을 사용하여 할인받은 금액을 관리하기 위해서, 주문목록에 쿠폰번호,할인총핵 속성을 추가함.
    * */
    private String couponName;
    private String couponType;
    private double couponValue;
    private String startDate;
    private String endDate;
}
