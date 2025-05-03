package org.example.bookpurchase.service;

import lombok.RequiredArgsConstructor;
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
    public void delete(Long book_number) {
        log.info("삭제:{}",book_number);
        cartListRepository.deleteById(book_number);
    }
}
