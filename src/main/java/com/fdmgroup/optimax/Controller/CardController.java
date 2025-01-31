package com.fdmgroup.optimax.Controller;

import com.fdmgroup.optimax.Model.Card;
import com.fdmgroup.optimax.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
public class CardController {

    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @QueryMapping
    public List<Card> getAllCards() {
        return cardService.getAllCards();
    }

    @QueryMapping
    public Card getCardById(@Argument int cardId) {

        try {
            Optional<Card> card = cardService.getCardById(cardId);
            if (card.isEmpty()) throw new NoSuchElementException();
            return card.get();
        } catch (NoSuchElementException e) {
            throw new RuntimeException();
        }
    }
}
