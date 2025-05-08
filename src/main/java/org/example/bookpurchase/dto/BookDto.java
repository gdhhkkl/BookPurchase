package org.example.bookpurchase.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.example.bookpurchase.domain.Book;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class BookDto {
    private Long book_number;
    private String title;
    private String writer;
    private String content;
    private Long price;


    public static BookDto of(Book book){//of는 파라미터가 한개일때 from은 여러개일때여..생성자함수.
        return BookDto.builder()
                .book_number(book.getBook_number())
                .title(book.getTitle())
                .writer(book.getWriter())
                .content(book.getContent())
                .price(book.getPrice())
                .build();
    }
    public static List<BookDto> ofList(List<Book> bookList){//tolist
        List<BookDto> bookDtoList = new ArrayList<>();
        for(Book book : bookList){
            bookDtoList.add(BookDto.of(book));
        }
        return bookDtoList;
    }




}
