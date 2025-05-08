package org.example.bookpurchase;

import org.example.bookpurchase.repository.CartListRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserEntityTest {
    @Autowired
    CartListRepository cartListRepository;
//    @Test
//    public void CartListTest(){
//        CartList cartList = CartList.builder()
//                .cartlistid("id")
//                .total("totl")
//                .build();
//        cartListRepository.save(cartList);
//    }
}
