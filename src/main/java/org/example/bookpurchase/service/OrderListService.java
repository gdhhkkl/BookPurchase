package org.example.bookpurchase.service;

import org.example.bookpurchase.domain.OrderList;
import org.example.bookpurchase.repository.OrderListRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderListService {
    private final OrderListRepository orderListRepository;

    public OrderListService(OrderListRepository orderListRepository) {
        this.orderListRepository = orderListRepository;
    }

    public OrderList findId(Long orderListId){
        return orderListRepository.findByOrderList_id(orderListId);
    }
}
