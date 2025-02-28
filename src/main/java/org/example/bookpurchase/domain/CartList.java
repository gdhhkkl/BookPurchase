package org.example.bookpurchase.domain;

import jakarta.persistence.*;
import lombok.*;
import org.example.bookpurchase.repository.CartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class CartList {
    private static final Logger log = LoggerFactory.getLogger(CartList.class);
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartList_id")
    private Long cartList_id;

    private Long book_total_price;

    private Long book_total_count;

    @ManyToOne(fetch = FetchType.EAGER)//LAZY하지않으면 걍 findall하면 타고타고가서 찾아와줌
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_number")
    private Book book;//연관관계

    public static CartList createCartList(Cart cart, Book book){
        CartList cartList = new CartList();

        cartList.setBook(book);
        cartList.setCart(cart);


        return cartList;
    }






}
