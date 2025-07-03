package org.example.bookpurchase;

import org.example.bookpurchase.controller.Home;
import org.example.bookpurchase.domain.Cart;
import org.example.bookpurchase.service.CartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@WebMvcTest(controllers = Home.class)
public class CartTest {
    private Home home;
    @Autowired
    private CartService cartService;

    @Test
    public void Cartfind() throws Exception{

    }
}
