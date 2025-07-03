package org.example.bookpurchase.dto;


import lombok.Getter;

@Getter
public class OrderNowDto {
    private Long bookNumber;
    private Long count;
    private Long price;
}
