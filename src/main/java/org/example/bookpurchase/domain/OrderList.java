package org.example.bookpurchase.domain;


import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "ordersList")
@Data//이거 하니까 setBook,setOrder생성됨...뭐임?
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


    public static OrderList createOrderList(Order order, Book book_number){//Book을 List<Book>이렇게 해서 하라는데..그러는이유가뭔가
        OrderList orderList = new OrderList();
        orderList.setOrder(order);
        orderList.setBook(book_number);
        return orderList;
    }

}
