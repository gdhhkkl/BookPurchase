package org.example.bookpurchase.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.FileNotFoundException;

@ControllerAdvice//모든 패키지에 적용
public class GlobalCatcher {
//    @ExceptionHandler({NullPointerException.class, FileNotFoundException.class})
//    public String catcher2(Exception ex){
//        return "error";
//    }
//    @ExceptionHandler(Exception.class)
//    public String catcher(Exception ex){
//        return "error";
//    }
}
