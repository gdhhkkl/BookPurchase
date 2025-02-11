package org.example.bookpurchase.service;


import lombok.RequiredArgsConstructor;
import org.example.bookpurchase.domain.Book;
import org.example.bookpurchase.dto.BookDto;
import org.example.bookpurchase.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
@Service//서비스개층   트랜잭션 처리를 한다.
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<BookDto> findAll(){
        List<Book> books = bookRepository.findAll();
        return BookDto.ofList(books);
    }


    public BookDto findById(Long book_number){
        Book book =bookRepository.findById(book_number).orElseThrow(()-> new NullPointerException("해당 책은 존재하지 않습니다."));
        return BookDto.of(book);
    }
    public  Book findByBookId(Long book_number){
        Book book = bookRepository.findByBookNumber(book_number);
        return book;
    }

}
