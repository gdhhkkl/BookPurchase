package org.example.bookpurchase.repository;

import org.example.bookpurchase.domain.Card;
import org.example.bookpurchase.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

//    @Query("select m from Card m where m.user.user_id = :userId")
    List<Card> findByUser(User userId);

}
