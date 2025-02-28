package org.example.bookpurchase.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.bookpurchase.domain.Order;

@AllArgsConstructor
@Getter
public class OrderDto {
    private Long postNumber;
    private String detailedAddress;
    private String basicAddress;

//    public Order orderDto(OrderDto orderDto){
//        Order order = new Order();
//        return order;
//    }


}
