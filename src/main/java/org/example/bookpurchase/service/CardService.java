package org.example.bookpurchase.service;

import lombok.RequiredArgsConstructor;
import org.example.bookpurchase.domain.Card;
import org.example.bookpurchase.domain.Cart;
import org.example.bookpurchase.domain.User;
import org.example.bookpurchase.dto.CardDto;
import org.example.bookpurchase.repository.CardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CardService {
    private static final Logger log = LoggerFactory.getLogger(CardService.class);
    @Autowired
    private final CardRepository cardRepository;
    @Autowired

    private UserService userService;
    public Card addCard(CardDto cardDto, Long userId){
        User user = userService.findByUserId(userId);
        return save(new Card(0L, //new Card()이 안에 넣는것도 .하는거랑 동일함
                cardDto.getCardNumber(),
                cardDto.getCardDate(),
                cardDto.getCardType(),
                user
        ));
    }
    @Transactional
    public Card save(Card card){
        return cardRepository.save(card);
    }

    public List<Card> findCard(Long userId){
        User user =userService.findByUserId(userId);
        List<Card> cards = cardRepository.findCardByUserId(user.getUser_id());
        log.info("뭐노:{}",cards);
        return cards;
    }
//    public List<Card> findById(Long userId){
//        User user = userService.findByUserId(userId);
//        List<Card> cards = cardRepository.findCardByUserId(user.getUser_id());
//        return cards;
//    }

}
