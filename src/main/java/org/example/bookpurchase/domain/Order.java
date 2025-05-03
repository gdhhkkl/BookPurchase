package org.example.bookpurchase.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.bookpurchase.dto.OrderDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "orders")
public class Order {

    private static final Logger log = LoggerFactory.getLogger(Order.class);
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long order_id;

//    @Column(nullable = false)
//    private Long order_price;

//    @Column(nullable = false)
//    private String card_type;

    @Column(nullable = false)
    private String card_number;
//
//    @Column(nullable = false)
//    private Long postNumber;

    @Column(nullable = false)
    private String basicAddress;

//    @Column(nullable = false)
//    private String detailAddress;


//  private String state;
//  private String cancel_date;
//  private String cancel_info;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User user;

//    @OneToMany
//    private List<OrderList> orderLists = new ArrayList<>();

    public void setUser(User user){
        this.user = user;
//        user.getOrders().add(this);
    }
    public void setBasicAddress(String basicAddress){
        this.basicAddress=basicAddress;
    }
    public void setCard_number(String card_number){
        this.card_number = card_number;
    }

//    public void setCard_type(String card_type){
//        this.card_type =card_type;
//    }


//    public void addOrderList(OrderList orderList){
//        orderLists.add(orderList);
//        orderList.setOrder(this);
//    }

//    public static Order createOrder(User user, OrderList... orderLists){
//        Order order = new Order();
//        order.setUser(user);
//        for(OrderList orderList: orderLists){
//            order.addOrderList(orderList);
//        }
//        return order;
//    }
    public static Order createOrder(User user,String basicAddress,String card_number){
        Order order =new Order();
        order.setUser(user);
        order.setBasicAddress(basicAddress);
        order.setCard_number(card_number);
        return order;
    }
//    public static Order saveAddress(String basicAddress){
//       Order. = basicAddress;
//    }



//    public static Order toEntity(String basicAddress, User user){
//        return Order.builder()
//                .basicAddress(basicAddress)
//                .user(user)
//                .build();
//    }


}
