package org.example.bookpurchase.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.bookpurchase.dto.CardDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Card {
    private static final Logger log = LoggerFactory.getLogger(Card.class);
    @Id
    @Column(name = "card_id")
//    @GeneratedValue(strategy = GenerationType.AUTO)
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
