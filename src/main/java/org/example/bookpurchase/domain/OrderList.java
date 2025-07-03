package org.example.bookpurchase.domain;


import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "ordersList")
//@Data//get/set 묶어진것
public class OrderList {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderList_id;

    private Long book_total_count;

    private Long book_total_price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Book book;

    public OrderList(Order order, Book book) {
        this.order = order;
        this.book =book;
    }

    public void setOrder(Order order){

        this.order =order;
    }

    public void setBook(Book book){
        this.book = book;
    }

    public void setCount(Long count){
        this.book_total_count = count;
    }

    public void setPrice(Long price){
        this.book_total_price = price;
    }
    // 쿠폰=주문목록에 쿠폰번호,할인총핵 속성을 추가함.

    /*
    * List<>순서가 있는 데이터의 집합 데이터의 중복을 허용
    * 객체를 일렬로 늘어놓은 구조
    * */

    public static List<OrderList> createOrderList(Order order, List<Book> books, Long count, Long price){
        log.info("orderList 책 값:{}",books);

        List<OrderList> orderList = new ArrayList<>();

        for(int i=0; i< books.size(); i++) {
            OrderList orders = new OrderList(order, books.get(i));

            log.info("나와여?:{}",books.get(i).getBook_number());
            orders.setCount(count);
            orders.setPrice(price);
            
            orderList.add(orders);


            log.info("{}", books.get(i).getBook_number());
        }

//        for(Book book: books){}//단순 숫자만 하면 삼항연산자

//        log.info("왜 그러냐?:{}",orderList.getBook().getBook_number());

//        books.stream().map(book -> {
//            orderList.setBook(book);
//        });

//        books.stream().collect(Collectors.toCollection())
//        Set<String> set = new HashSet<>();// 이런것도 있음
        return orderList;
    }



}
