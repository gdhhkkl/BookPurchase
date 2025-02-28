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
    //DTO라는것은 user이라는 객체를 쓸때 전부를 다 충족해야하는데 내가 필요한것은 아이디랑 이름 이것만 어느 부분에 쓰다 할때 dto에서 준다. 그래서 보통 dto의 이름을 request뭐시기라고 한다.

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
