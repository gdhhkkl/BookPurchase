package org.example.bookpurchase.service;

import lombok.RequiredArgsConstructor;
import org.example.bookpurchase.domain.*;
import org.example.bookpurchase.dto.BookDto;
import org.example.bookpurchase.dto.CartDto;
import org.example.bookpurchase.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CartService {

    private final CartListRepository cartListRepository;
    private final CartRepository cartRepository;
    private final UserService userService;
    private final BookService bookService;


    @Transactional
    public Long cart( Long userid, Long book_number){

//        User user= userRepository.findByUserId(user_id);
//        Book book = bookRepository.findByBookNumber(book_number); 순환참조문제가 발생하는 것을 막기위해 service로 해야한다.

        User user = userService.findByUseId(userid);

        Book book = bookService.findByBookId(book_number);

//        CartList.createCartLit(cart, book);

        Cart carts =cartRepository.findByCartId(user.getUser_id());
        //해당유저에 장바구니가 있나?
        if(carts!=null) {
            Cart cart =Cart.createCart(user);
            cartRepository.save(cart);
        }


//            Cart cart = Cart.createCart(user);
//
//            cartRepository.save(cart);

            CartList list = CartList.createCartList(carts, book);

            cartListRepository.save(list);
            // 1번 책 가격 검색 (book)
            // 1번책 가격 x 개수 = 이책총가격

            // inner join 전체 검색
            // left outer join 왼쪽으로 붙여줌
            // 대용량 데이터베이스 솔루션 ,,
            // fetch join

            //쿼리짜서 조회하기
            //inner->fetch로 변화해서 해야함

        return list.getCartList_id();
    }

    public List<CartList> findAll(){
        List<CartList> cart = cartListRepository.findAll();
        return cart;
    }


















}



