package org.example.bookpurchase.domain;

import jakarta.persistence.*;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Getter
@Entity
public class Cart {
    private static final Logger log = LoggerFactory.getLogger(Cart.class);
    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cart_id;

    @ManyToOne(fetch = FetchType.LAZY)//글로벌 로딩 전략
    @JoinColumn(name = "user_id")
    private User user; 

//    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<CartList> cartLists = new ArrayList<>();G

    //===연관관계 메서드==//
    public void setUser(User user){
//        log.info("setUSer:{}",user.getUser_id());
        this.user =user;
//        user.getCarts().add(this);
    }


//    public void addCartList(CartList cartList){
//        cartLists.add(cartList);
//        cartList.setCart(this);
//    }


//    public static Cart createCart(User user, CartList... cartLists){ // 디미터법칙:다른 객체가 어떠한 자료를 갖고 있는지 속사정을 몰라야 한다는 것을 의미 (결합도)
//                                                                     //캡슐화를 높혀 객체의 자율성과 응집도를 높일 수 있다.
//        Cart cart = new Cart();
//
//        cart.setUser(user);
////        cart.addCartList(cartLists);
//        for(CartList cartList : cartLists){
//            cart.addCartList(cartList);
//        }
//        return  cart;
//    }
        public static Cart createCart(User user){
            Cart cart = new Cart();
            cart.setUser(user);
            return cart;
        }


}
