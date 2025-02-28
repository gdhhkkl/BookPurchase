package org.example.bookpurchase.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.example.bookpurchase.dto.AddressDTO;
import org.example.bookpurchase.dto.OrderDto;

@Getter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long order_id;

    private Long order_price;

    private String card_type;

    private String card_number;

    private Long postNumber;

    private String basicAddress;

    private String detailAddress;


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
    public void setBasicAddress(AddressDTO addressDTO){
        this.basicAddress= String.valueOf(addressDTO);
    }
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
    public static Order createOrder(User user){
        Order order =new Order();
        order.setUser(user);
        return order;
    }
    public static Order save(AddressDTO addressDTO){
        Order order = new Order();
        order.setBasicAddress(addressDTO);
        return  order;
    }


}
