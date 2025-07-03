package org.example.bookpurchase.repository;

import org.example.bookpurchase.domain.Book;
import org.example.bookpurchase.domain.Cart;
import org.example.bookpurchase.domain.CartList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository//데이터 엑세스 계층
public interface CartListRepository extends JpaRepository<CartList, Long> {
    @Query("select m from  CartList m where  m.cartList_id = : cartList_id")
    CartList findByCartListId(@Param("cartList_id") Long cartList_id);

    @Query("select m from CartList m where m.cart.cart_id = :cart_id")
    List<CartList> findCartListByCartId(Long cart_id);

    void deleteByBookAndCart(Book book, Cart cart);

    @Query("select m from CartList m where m.cart.cart_id = :cart and m.book.book_number = :book")
    List<CartList> findByCart_idAndBook_number(@Param("cart") Long cart, @Param("book") Long book);

    CartList findByBook(Book book);



}
