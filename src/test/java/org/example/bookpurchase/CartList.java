package org.example.bookpurchase;

import org.example.bookpurchase.repository.CartListRepository;
import org.example.bookpurchase.repository.CartRepository;
import org.example.bookpurchase.service.CartListService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CartList {
    @Autowired CartRepository cartRepository;
    @Autowired
    private CartListService cartListService;
    @Autowired
    private CartListRepository cartListRepository;

//    @Test
//    void findBook(Long cartID){
//        cartListRepository.findCartListByBook(cartID);
//    }//에러남

}
