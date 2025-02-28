package org.example.bookpurchase.service;

import lombok.RequiredArgsConstructor;
import org.example.bookpurchase.domain.*;
import org.example.bookpurchase.dto.AddressDTO;
import org.example.bookpurchase.dto.OrderDto;
import org.example.bookpurchase.repository.OrderListRepository;
import org.example.bookpurchase.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    private final UserService userService;
    private final BookService bookService;
    private final OrderRepository orderRepository;
    private final OrderListRepository orderListRepository;

    public Order order(Long userid, Long bookNumber){//Long
        User user = userService.findByUserId(userid);

        Book book = bookService.findByBookId(bookNumber);

        Order orders = orderRepository.findByOrderId(user.getUser_id());

        if(orders==null){
            Order order = Order.createOrder(user);//이게 왜 하나만 받아지냐?
            orderRepository.save(order);
            OrderList orderList = OrderList.createOrderList(order, book);

            log.info("주문에서:{}",orderList.getBook());

            orderListRepository.save(orderList);

        } else if (orders!=null) {
            OrderList orderList = OrderList.createOrderList(orders, book);
            orderListRepository.save(orderList);
        }
        return orders;
    }

    @Transactional
    public List<OrderList> findOrder(Long userId){
        User user = userService.findByUserId(userId);
        Order order = orderRepository.findByOrderId(user.getUser_id());
        List<OrderList> orderLists = orderListRepository.findOrderListByOrder(order.getOrder_id());
        return orderLists;
    }

    public Order save(AddressDTO addressDTO,Long userId){
        User user = userService.findByUserId(userId);

        Order orDer = orderRepository.findByOrderId(user.getUser_id());
        if(orDer==null){
            orderRepository.save(Order.save(addressDTO));
        }
        return orDer;

        //주문테이블에 1번유저 찾기
        //1번 유저의 address 넣기

    }



}
