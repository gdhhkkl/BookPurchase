package org.example.bookpurchase.repository;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.bookpurchase.domain.Cart;
import org.example.bookpurchase.dto.CartDto;
import org.example.bookpurchase.security.UserLogin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartRepositoryS {
    private static final Logger log = LoggerFactory.getLogger(CartRepositoryS.class);

    private final EntityManager em;//주입안된상태


    public CartRepositoryS(EntityManager em) {
        this.em = em;
    }

//    public List<CartDto> findCart (){
//      String jpql = """
//              select new (org.example.bookpurchase.dto.CartDto from Cart m join fetch m.cartLists""";
//      List<CartDto> carts = em.createQuery(jpql, CartDto.class).getResultList();
//      return carts;
//    }

}
