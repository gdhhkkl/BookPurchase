package org.example.bookpurchase.repository;


import org.example.bookpurchase.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

//    @Query("select m from  Order m where m.order_ id = :orderId")
//    Order findByOrderId(Long orderId);

    @Query("select m from Order m join fetch  m.user " )//SELECT m*, t* FROM Order m INNER JOIN User t ON m.user_id=t.userid;
    List<Order> findOrderWithUser(Long userId);

}
