package org.example.bookpurchase;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.example.bookpurchase.domain.Cart;
import org.example.bookpurchase.repository.CartRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@RequiredArgsConstructor
public class CartServiceTest {


    @Autowired CartRepository cartRepository;


    @Test
    void toCartDto() {
        List<Cart> list = cartRepository.findCartFetch();

        Assertions.assertThat(list).isNotNull();
//        Assertions.assertThat().isEqualTo(Long.class);

    }
}
