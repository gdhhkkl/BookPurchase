package org.example.bookpurchase.domain;

import jakarta.persistence.*;
import lombok.*;
import org.example.bookpurchase.dto.BookDto;


@Getter
@Entity
public class Book {
    @Id//특정속성을 기본키로 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOK_NUMBER")
    private Long book_number;
    //@Column는 객체 필드를 데이블 컬럼과 매핑할때 여러 속성과 기능들이 있음
    @Column(nullable = false)
    private  String title;
    @Column(nullable = false)
    private  String writer;
    @Column(nullable = false)
    private  String content;
    @Column(nullable = false)
    private  Long price;

    @Column(nullable = false)
    private  Long count;




}
