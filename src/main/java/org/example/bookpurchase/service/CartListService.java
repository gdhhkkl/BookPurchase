package org.example.bookpurchase.service;

import lombok.RequiredArgsConstructor;
import org.example.bookpurchase.domain.Book;
import org.example.bookpurchase.domain.Cart;
import org.example.bookpurchase.domain.CartList;
import org.example.bookpurchase.repository.CartListRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CartListService {

    private static final Logger log = LoggerFactory.getLogger(CartListService.class);
    private final CartListRepository cartListRepository;

    private final BookService bookService;
    private final CartService cartService;

//    public CartList findBook(Long cart_id){
//        log.info("{}",cart_id);
//
//        CartList cartList = cartListRepository.findByCartListId(cart_id);
//
//        log.info("카드서비스 : {}",cartList.getBook().getBook_number());
//
//        return cartList;
//    }

    public List<CartList> findCartList(Long cartId){
        List<CartList> cartLists = cartListRepository.findCartListByCartId(cartId);
        return cartLists;
    }
    @Transactional
    public void delete(Long cart_id, Long book_number) {
        log.info("삭제:{}",book_number);
        //조건문이 cart로 되어지니까 그것에 맞지 않는게 나오니 그런거임 1번이 된 이유는 cart가 1번이니까 아다리가 맞아서 그런거임
        Book book = bookService.findById(book_number);
        Cart cart = cartService.findById(cart_id);

        cartListRepository.deleteByBookAndCart(book, cart);

    }


}
