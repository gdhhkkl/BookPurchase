package org.example.bookpurchase.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor
@Getter
public class CardDto {
    private String cardNumber;
    private String cardType;
    private String cardDate;
}
