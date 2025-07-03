package org.example.bookpurchase.service;

import lombok.RequiredArgsConstructor;
import org.example.bookpurchase.domain.*;
import org.example.bookpurchase.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final OrderListService orderListService;
    private final CouponListService couponListService;
    private final CouponRepository couponRepository;
    private final CouponListRepository couponListRepository;


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

    public Order saveOrder(String basicAddress,String cardNumber,Long userid, Long savePrice, String couponType){
        User user = userService.findByUserId(userid);

        Cart cart = cartRepository.findByCartId(user.getUser_id());

        List<CartList> cartLists = cartListService.findCartList(cart.getCart_id());

        List<Book> book = bookService.findBookList(CartList.toBookIds(cartLists));
        
//        CouPonList couPonList = couponListRepository.findByCoupon(couponType.getId()); 여기서 조인시켜야할뜻요->사용유무 지금 푸콘 리스트에있음
        CouPonList coupon1 = couponListService.findCoupon(couponType);
        Long couponId = coupon1.getId();
        log.info("쿠폰아이디:{}",couponId);
        CouPonList couPon = couponListRepository.findByCouponListId(couponId);


//        Coupon coupon = couponRepository.findById(couponType.getId()).orElseThrow(()->new NullPointerException("선택하신 쿠폰을 못찾겠습니다."));

        Order order = Order.createOderSave(user,basicAddress,cardNumber,savePrice,couPon);
        orderRepository.save(order);

        for(int i=0; i<cartLists.size(); i++){
            Long count = cartLists.get(i).getBook_total_count();
            Long price = cartLists.get(i).getBook_total_price();
            List<OrderList>  orderList = OrderList.createOrderList(order,book,count,price);
            orderListRepository.save(orderList.get(i));
        }

        cartListRepository.deleteAll(cartLists);
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
    @Transactional
    public void delete(Long orderListId) { // 이거 리스트에서 삭제하는게 아니라 order쪽에서 삭제해하하는거야? 그럼 기록은????
        OrderList orderList = orderListService.findId(orderListId);
        orderListRepository.delete(orderList);
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
    @Transactional
    public Order orderNowBook ( Long userid, String basicAddress,String cardNumber,Long book_number,Long countNumber,Long price){
        User user = userService.findByUserId(userid);
        log.info("orderNowBook 잘 들어왔는지 학인:{}{}{}",book_number,countNumber, price);

        List<Book> orderNowBook = bookService.findByID(book_number);
        Book book =bookService.findByBookId(book_number);


        Order order = Order.createOrder(user,basicAddress,cardNumber);

        orderRepository.save(order);
        for (int i=0; i<1; i++){
            List<OrderList> orderLists = OrderList.createOrderList(order,orderNowBook,countNumber,price);
            orderListRepository.save(orderLists.get(i));
        }

        return order;
    }




}
