package org.example.bookpurchase.repository;

import org.example.bookpurchase.domain.Book;
import org.example.bookpurchase.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("select m from Book m where m.book_number = :book_number")
    Book findByBookNumber(Long book_number);
    @Query("select m from Book m where m.price = :price")
    Book findBookPrice(Long price);
    @Query("select m from Book m where m.book_number = :id")
    List<Book> findByBook_number(Long id);
}
