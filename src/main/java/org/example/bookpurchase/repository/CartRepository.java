package org.example.bookpurchase.repository;

import org.example.bookpurchase.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.w3c.dom.ls.LSInput;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
//    @Query("select m from Cart m where m.user.user_id = :user_id")
//    Cart findByUserId(Long user_Id);

    @Query("select m from Cart m where m.cart_id = :cartId")
    Cart findByCartId(Long cartId);

    @Query("select m from Cart m join fetch m.cartLists join fetch m.user")
    List<Cart> findCartFetch();




}