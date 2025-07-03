package org.example.bookpurchase.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Message {
    private String message; //메서제
    private String href; //이동헤야할 화면 설정

    public Message(String message,String href){
        this.message = message;
        this.href =href;
    }
//    public void setMessage (String message, String href){
//        this.message =message;
//        this.href =href;
//    }
}
