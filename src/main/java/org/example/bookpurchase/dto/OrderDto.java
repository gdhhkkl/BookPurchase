package org.example.bookpurchase.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.example.bookpurchase.domain.Order;
import org.example.bookpurchase.domain.User;

@Builder
@AllArgsConstructor
@Getter
public class OrderDto {
    private User user;
    private String basicAddress;

   public Order of(User user, String basicAddress){
       return Order.builder()
               .basicAddress(basicAddress)
               .user(user)
               .build();
   }


}
