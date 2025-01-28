package com.fdmgroup.optimax.Service;

import com.fdmgroup.optimax.Repository.CardBenefitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardBenefitService {

    private final CardBenefitRepository cardBenefitRepository;

    @Autowired
    public CardBenefitService(CardBenefitRepository cardBenefitRepository) {
        this.cardBenefitRepository = cardBenefitRepository;
    }
}
