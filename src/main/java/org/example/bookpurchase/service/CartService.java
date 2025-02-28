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
public class CartService {

    private static final Logger log = LoggerFactory.getLogger(CartService.class);
    private final CartListRepository cartListRepository;
    private final CartRepository cartRepository;
    private final UserService userService;
    private final BookService bookService;


    @Transactional
    public Long cart( Long userid, Long book_number){ //Long count
        //repository 하면...뭔 문제 생긴다고 함...그게 뭐였더라.
        User user = userService.findByUserId(userid);

        Book book = bookService.findByBookId(book_number);


        Cart carts =cartRepository.findByCartId(user.getUser_id());//=>d이걸 쓰니 당연히 널이게지

        log.info("카트기 확인:{}",carts);

        if(carts==null) {
            userService.findByUserId(userid);
            Cart cart =Cart.createCart(user);
            cartRepository.save(cart);
            CartList list = CartList.createCartList(cart, book);
            cartListRepository.save(list);

        }
        else if (carts!=null) {
            CartList list = CartList.createCartList(carts, book);
            cartListRepository.save(list);
        }


//            Book book1 =bookService.findBookPrice(book.getPrice());

//            Long totalPrice = book.getPrice()*count;
            // 1번 책 가격 검색 (book)
            // 1번책 가격 x 개수 = 이책총가격

            // inner join 전체 검색
            // left outer join 왼쪽으로 붙여줌
            // 대용량 데이터베이스 솔루션 ,,
            // fetch join

            //쿼리짜서 조회하기
            //inner->fetch로 변화해서 해야함

        return user.getUser_id();
    }


    @Transactional
    public List<CartList> findCart(Long userId){
        User user = userService.findByUserId(userId);

        Cart cart = cartRepository.findByCartId(user.getUser_id());
//        log.info("몇번 카드 : {}",cart.getCart_id());

        List<CartList> cartLists =cartListRepository.findCartListByCartId(cart.getCart_id());
        return cartLists;
    }




















}



