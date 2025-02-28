package org.example.bookpurchase.repository;


import org.example.bookpurchase.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select m from  Order m where m.order_id = :orderId")
    Order findByOrderId(Long orderId);


}
