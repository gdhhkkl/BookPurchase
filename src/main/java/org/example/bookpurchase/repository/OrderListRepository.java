package org.example.bookpurchase.repository;


import org.example.bookpurchase.domain.OrderList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderListRepository extends JpaRepository<OrderList,Long> {
    @Query("select m from OrderList m where m.order.order_id = :orderId")
    List<OrderList> findOrderListByOrder(Long orderId);
}
