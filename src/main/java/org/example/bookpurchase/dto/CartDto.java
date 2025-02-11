package org.example.bookpurchase.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.example.bookpurchase.domain.Book;
import org.example.bookpurchase.domain.Cart;
import org.example.bookpurchase.domain.CartList;

import java.util.ArrayList;
import java.util.List;
@Getter
@Builder
@AllArgsConstructor
public class CartDto {
    private Long cartList_id;
    private Long book_number;
    private String title;
    private String writer;
    private Long price;


//    public static CartDto to(Cart cart) {
//        return CartDto.builder()
//                .cart_id(cart.getCart_id())
//                .cartList_id(cartList.getCartList_id())
//                .book_number(book.getBook_number())
//                .title(book.getTitle())
//                .writer(book.getWriter())
//                .price(book.getPrice())
//                .build();
//    }


//    public static List<CartDto> toList(List<Cart> cartList, ){
//        List<CartDto> cartDto = new ArrayList<>();
//        for(Cart item : cartDto){
//            to()
//        }
//        return cartDto;
//    }


}
