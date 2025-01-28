package com.fdmgroup.optimax.Model;

import com.fdmgroup.optimax.ENUM.BenefitType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardBenefit {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CardBenefit_generator")
    @SequenceGenerator(name = "CardBenefit_generator", sequenceName = "CardBenefit_seq", allocationSize = 1)
    private int cardBenefitId;
    @Enumerated(EnumType.STRING)
    private BenefitType benefitType;
    private Double benefitRate;
    private String condition;
    @ManyToOne
    @JoinColumn(name = "FK_cardId")
    private Card card;

    public CardBenefit(BenefitType benefitType, Double benefitRate, String condition, Card card) {
        this.benefitType = benefitType;
        this.benefitRate = benefitRate;
        this.condition = condition;
        this.card = card;
    }
}
