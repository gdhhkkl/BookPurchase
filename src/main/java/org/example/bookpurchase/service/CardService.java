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


    private final CardRepository cardRepository;


    private final UserService userService;
    public Card addCard(CardDto cardDto, Long userId){
        User user = userService.findByUserId(userId);
        return save(new Card(0L, //new Card()이 안에 넣는것도 .하는거랑 동일함
                cardDto.getCardNumber(),
                cardDto.getCardDate(),
                cardDto.getCardType(),
                user
        ));
    }
//    @Transactional
    public Card save(Card card){
        return cardRepository.save(card);
    }
//    @Transactional
    public List<Card> findCard(Long userId){
        User user =userService.findByUserId(userId);
        List<Card> cards = cardRepository.findByUser(user);
//        log.info("뭐노:{}",cards.get(0).getCard_id());
        return cards;
    }
//    public List<Card> findById(Long userId){
//        User user = userService.findByUserId(userId);
//        List<Card> cards = cardRepository.findCardByUserId(user.getUser_id());
//        return cards;
//    }

}
