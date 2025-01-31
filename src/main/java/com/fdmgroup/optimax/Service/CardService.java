package com.fdmgroup.optimax.Service;

import com.fdmgroup.optimax.Model.Card;
import com.fdmgroup.optimax.Repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardService {

    private final CardRepository cardRepository;

    @Autowired
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    public Optional<Card> getCardById(int cardId) {
        return cardRepository.findById(cardId);
    }
}
