package org.example.bookpurchase.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Card {

    @Id
    @Column(name = "card_id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long card_id;
    @Column(nullable = false)
    private String cardNumber;
    @Column(nullable = false)
    private String cardType;
    @Column(nullable = false)
    private String cardDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User user;




}
