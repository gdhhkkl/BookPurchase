package org.example.bookpurchase.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.bookpurchase.domain.Book;
import org.example.bookpurchase.domain.CartList;
import org.example.bookpurchase.dto.BookDto;
import org.example.bookpurchase.repository.BookRepository;
import org.example.bookpurchase.security.UserLoginArgumentResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service//서비스개층   트랜잭션 처리를 한다.
@RequiredArgsConstructor
public class BookService {
    @Autowired
    private final BookRepository bookRepository;

    public List<BookDto> findAll(){
        List<Book> books = bookRepository.findAll();
        return BookDto.ofList(books);
    }


    //public BookDto findById(Long book_number){//상세페이지
      //  Book book =bookRepository.findById(book_number).orElseThrow(()-> new NullPointerException("해당 책은 존재하지 않습니다."));
        //return BookDto.of(book);
    //}

    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new NullPointerException("해당 책은 존재하지 않습니다."));
    }

    //

    public List<Book> findByID(Long id){
        return bookRepository.findByBook_number(id);
    }

    public  Book findByBookId(Long book_number){
        Book book = bookRepository.findByBookNumber(book_number);
        return book;
    }

    public List<Book> findBookList(List<Long> ids ){//for문을 통해서 하나를 받아서 묶어서 반환
        // for 문 해서 담아서 던져야게지?
        log.info("책 잘 :{}",ids);//16,14
        List<Book> book = new ArrayList<>();

        for(int i=0 ; i<ids.size(); i++) {
            book.add(bookRepository.findByBookNumber(ids.get(i)));
            log.info("돌아가게지?:{}",book.get(i).getBook_number());//왜 16에서 돌냐? get(0)이 0번째 자리만 get한다는 것으로 해석되어서 16만 나온거임 바부야
        }
        log.info("나와랏 두개:{}",book);
        return book;
    }

    public List<Book> findBookId(Long book_number){
        return bookRepository.findByBook_number(book_number);
    }

}
