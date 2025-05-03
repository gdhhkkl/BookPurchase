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
    private final UserRepository userRepository;


    @Transactional /// 카트가 이상하게 만들어짐
    public Long cart( Long userid, Long book_number, Long count){ //Long count
        //repository 하면...뭔 문제 생긴다고 함...그게 뭐였더라.
        User user = userService.findByUserId(userid);//유저찾음

        Book book = bookService.findByBookId(book_number);//북 찾음
//        User user3 = userService.findCart(userid);

        Cart carts =cartRepository.findByCartId(user.getUser_id());//=>d이걸 쓰니 당연히 널이게지 //여기가 문제였어?
//        log.info("카드 찾기:{}",carts.getUser().getUser_id());

//        User user1 = userRepository.findById(carts.getCart_id()).orElseThrow(()->new NullPointerException("카트기가 없음"));//작동안됨..ㅎ
        log.info("책수량 몇개:{}",count);
        Long totalPrice = book.getPrice()*count;
        log.info("수량가격:{}",totalPrice);
        //뭔 문제임 카트기를 두번 생성하고 두번째 카트기에 걍 넣어버리네 아 진짜 2번유저가 하지도않았는데 2번 유저의 상품으로 들어가고 이게 뭐녀
        if(carts==null) {
//            userService.findByUserId(userid);//이거 왜 있는거미?

            Cart cart =Cart.createCart(user);

            cartRepository.save(cart);
            CartList list = CartList.createCartList(cart, book, totalPrice, count);
            cartListRepository.save(list);

        }
        else if (carts!=null) {
            CartList list = CartList.createCartList(carts, book , totalPrice ,count);
            cartListRepository.save(list);
        }


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
//        log.info("몇번 카드 : {}",cart.getCart_id());//카드가 없어서 널 나오니까 에러 나옴

        List<CartList> cartLists =cartListRepository.findCartListByCartId(cart.getCart_id());
        return cartLists;
    }


//    @Transactional
//    public Cart findCartById(Long userId){
//        User user = userService.findByUserId(userId);
//        Cart cart = cartRepository.findByCartId(user.getUser_id());
//        return cart;
//    }

    public Long cartTotalPrice(List<CartList> cartLists){
        Long result = 0L;

        for(CartList item: cartLists){
            result += item.getBook_total_price();
        }


        return result;
    }




















}



