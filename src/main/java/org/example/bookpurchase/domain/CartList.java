package org.example.bookpurchase.domain;

import jakarta.persistence.*;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.CreatedDate;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class CartList extends LocalDate {
    private static final Logger log = LoggerFactory.getLogger(CartList.class);
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartList_id")
    private Long cartList_id;

    private Long book_total_price;

    private Long book_total_count;



    @ManyToOne(fetch = FetchType.EAGER)//LAZY하지않으면 걍 findall하면 타고타고가서 찾아와줌    cascade=CascadeType.REMOVE 이거하니까 cart가 null됨
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_number")
    private Book book;//연관관계

    public void setCount(Long count){
        this.book_total_count = count;
    }
    public void setPrice(Long price){
        this.book_total_price = price;
    }

    public static CartList createCartList(Cart cart, Book book, Long price, Long count){//파라미터 잘못 자리 지정해서 반대로 들어감....학ㅆ..
        CartList cartList = new CartList();

        cartList.setBook(book);
        cartList.setCart(cart);
        cartList.setPrice(price);
        log.info("카드 총 가격 : {}",price);
        cartList.setCount(count);

        return cartList;
    }


    public static List<Long> toBookIds(List<CartList> list) {//static을 싫어하는이유가 난발해서 써버리면 쌓이니까 문제가 디서
        List<Long> result = new ArrayList<>();//담아서 던져줘야하니까 담는거 한나 만듬

        for(int i=0; i<list.size(); i++) {
            result.add(list.get(i).getBook().getBook_number());
        }
        log.info("책들어간다.:{}",result);
        return result;
    }




}
