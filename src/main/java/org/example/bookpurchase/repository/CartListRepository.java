package org.example.bookpurchase.repository;

import org.example.bookpurchase.domain.CartList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository//데이터 엑세스 계층
public interface CartListRepository extends JpaRepository<CartList, Long> {
    @Query("select m from  CartList m where  m.cartList_id = : cartList_Id")
    CartList findByCartListId(Long cartList_Id);

    @Query("select m from CartList m where m.cart.cart_id = :cart_id")
    List<CartList> findCartListByCartId(Long cart_id);

}
