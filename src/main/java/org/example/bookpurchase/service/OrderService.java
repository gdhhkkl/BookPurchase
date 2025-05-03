package org.example.bookpurchase.service;

import lombok.RequiredArgsConstructor;
import org.example.bookpurchase.domain.*;
import org.example.bookpurchase.dto.AddressDTO;
import org.example.bookpurchase.dto.OrderDto;
import org.example.bookpurchase.repository.CartListRepository;
import org.example.bookpurchase.repository.CartRepository;
import org.example.bookpurchase.repository.OrderListRepository;
import org.example.bookpurchase.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    //주문은 한번에 여러개 상품을 담았을때 같은 주문 번호로 묶기 위해 orderList 필요한것
//    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    private final UserService userService;
    private final BookService bookService;
    private final OrderRepository orderRepository;
    private final OrderListRepository orderListRepository;
    private final CartRepository cartRepository;
    private final CartListService cartListService;
    private final CartListRepository cartListRepository;


    @Transactional
    public Order order(Long userid,String basicAddress,String cardNumber){
        Long count=0L;
        Long price =0L;
        User user = userService.findByUserId(userid);


        Cart cart = cartRepository.findByCartId(user.getUser_id());

//        log.info("유저장바구니 :{}",cart.getCart_id());//유저 장바구니 ...걍 유저 찾는거 아니가?

        List<CartList> cartLists = cartListService.findCartList(cart.getCart_id());//1번카트기의 카트리스트 가져와서 담아

        List<Book> book = bookService.findBookList(CartList.toBookIds(cartLists));


//        List<Integer> list = new ArrayList<>();
        // wrapper 타입, 원시타입
        // Long long-->제러럴
//
//        list.add(1);
//        Order orders = orderRepository.findByBasicAddress(basicAddress);

        Order order = Order.createOrder(user,basicAddress,cardNumber);


        orderRepository.save(order);//주소에 null =false 해서ㅔ null 들어옴 , 저장되지 않은 인스턴스를 참조하는 연관이 있는 엔티티를 저장할 때


        for(int i =0; i<cartLists.size(); i++){
            count = cartLists.get(i).getBook_total_count();
            log.info("책 하나 갯수 :{}", count);
            price = cartLists.get(i).getBook_total_price();
            log.info("책 하나의 값 :{}",price);
            List<OrderList>  orderList = OrderList.createOrderList(order,book,count,price);
            orderListRepository.save(orderList.get(i));
        }

//        List<OrderList> orderList = OrderList.createOrderList(order,book,count,price);

//        for (int i =0; i<orderList.size(); i++) {
////            OrderList orderList1= orderList.stream().map
//            orderListRepository.save(orderList.get(i));
//        }

        cartListRepository.deleteAll(cartLists);

//            orderRepository.save(Order.toEntity(basicAddress,user));

//        } else if (orders!=null) {
//            OrderList orderList = OrderList.createOrderList(orders, book);
//            orderListRepository.save(orderList);
//            orderRepository.save(Order.toEntity(basicAddress,user));
//



        return order;
    }

    @Transactional
    public List<OrderList> findOrder(Long userId){

        User user = userService.findByUserId(userId);

        log.info("유저확인해보겠음:{}",user.getUser_id());

//        List<Order> orders = orderRepository.findOrderWithUser(user.getUser_id());

//        List<OrderList> orderLists = new ArrayList<>();
//        Order order = orderRepository.findByOrderId(user.getUser_id());
//        for(int i=0; i<orders.size(); i++) {
//            orderLists = orderListRepository.findOrderListByOrder(orders.get(i).getOrder_id());
//        }
//        List<OrderList> orderLists = orderListRepository.findOrderListByOrder(user.getUser_id());
        List<OrderList> orderLists = orderListRepository.findOrderList(user.getUser_id());
        return orderLists;
    }
//    @Transactional
//    public Order save(String address ,Long userId){
//        User user = userService.findByUserId(userId);
//
//        Order orDer = orderRepository.findByOrderId(user.getUser_id());
//        log.info("service 주문자:{}", orDer.getOrder_id());
//        if(orDer!=null) {
//            orderRepository.save(Order.adds(address));
//
//        }
//        return orDer;
//
//        //주문테이블에 1번유저 찾기
//        //1번 유저의 address 넣기
//
//    }




}
