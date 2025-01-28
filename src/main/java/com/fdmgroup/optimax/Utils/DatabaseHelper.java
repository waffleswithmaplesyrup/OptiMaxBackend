package com.fdmgroup.optimax.Utils;

import com.fdmgroup.optimax.ENUM.Bank;
import com.fdmgroup.optimax.ENUM.BenefitType;
import com.fdmgroup.optimax.ENUM.CapRate;
import com.fdmgroup.optimax.ENUM.Issuer;
import com.fdmgroup.optimax.Model.Card;
import com.fdmgroup.optimax.Model.CardBenefit;
import com.fdmgroup.optimax.Model.User;
import com.fdmgroup.optimax.Model.UserCard;
import com.fdmgroup.optimax.Repository.CardBenefitRepository;
import com.fdmgroup.optimax.Repository.CardRepository;
import com.fdmgroup.optimax.Repository.UserCardRepository;
import com.fdmgroup.optimax.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class DatabaseHelper {

    private final UserRepository userRepository;
    private final CardRepository cardRepository;
    private final CardBenefitRepository cardBenefitRepository;
    private final UserCardRepository userCardRepository;

    @Autowired
    public DatabaseHelper(UserRepository userRepository, CardRepository cardRepository, CardBenefitRepository cardBenefitRepository, UserCardRepository userCardRepository) {
        this.userRepository = userRepository;
        this.cardRepository = cardRepository;
        this.cardBenefitRepository = cardBenefitRepository;
        this.userCardRepository = userCardRepository;
    }

    public void saveCardTypes() {
        cardRepository.saveAll(Arrays.asList(
            // miles
            new Card(Issuer.MASTERCARD, Bank.CITIBANK, "Citi PremierMiles Card", "/assets/cards/Citi_PremierMiles_Card.png", 0, 0, CapRate.UNLIMITED),
            // cashback
            // zero cap means unlimited
            new Card(Issuer.MASTERCARD, Bank.STANDARD_CHARTERED, "Standard Chartered Simply Cash Credit Card", "/assets/cards/Standard_Chartered_Simply_Cash_Credit_Card.png", 0, 0, CapRate.UNLIMITED),
            // points
            new Card(Issuer.MASTERCARD, Bank.CITIBANK, "Citi Rewards Card", "/assets/cards/Citi_Rewards_Card.png", 0, 9_000, CapRate.MONTHLY),
            // cashback
            new Card(Issuer.VISA, Bank.OCBC, "OCBC 365 Credit Card", "/assets/cards/OCBC_365_Credit_Card.png", 800, 80, CapRate.MONTHLY),
            // points
            new Card(Issuer.VISA, Bank.STANDARD_CHARTERED, "Standard Chartered Rewards+ Credit Card", "/assets/cards/Standard_Chartered_Rewards+_Credit Card.png", 0, 20_000, CapRate.YEARLY)
        ));
    }

    public void saveCardBenefits() {
        List<Card> cards = cardRepository.findAll();

        cardBenefitRepository.saveAll(Arrays.asList(
                // Citi PremierMiles Card
                new CardBenefit(BenefitType.MILES, 1.2, "Local Spending", cards.get(0)),
                new CardBenefit(BenefitType.MILES, 2.2, "Foreign Spending (retail & online)", cards.get(0)),
                // SC Simply Cash Credit Card
                new CardBenefit(BenefitType.CASHBACK, 1.5, "Caltex, Petrol, Dining, Shopping", cards.get(1)),
                // Citi Rewards
                new CardBenefit(BenefitType.POINTS, 10.0, "Online: grocery, food delivery, ride-hailing", cards.get(2)),
                new CardBenefit(BenefitType.POINTS, 10.0, "In-store: department store, clothing store", cards.get(2)),
                new CardBenefit(BenefitType.POINTS, 1.0, "All other purchases", cards.get(2)),
                // OCBC 365
                new CardBenefit(BenefitType.CASHBACK, 3.0, "Watsons, Streaming, EV Charging", cards.get(3)),
                new CardBenefit(BenefitType.CASHBACK, 5.0, "Dining", cards.get(3)),
                new CardBenefit(BenefitType.CASHBACK, 6.0, "Fuel at Petrol Stations", cards.get(3)),
                // SC Rewards+
                new CardBenefit(BenefitType.POINTS, 10.0, "Foreign spending: retail, dining, travel", cards.get(4)),
                new CardBenefit(BenefitType.POINTS, 5.0, "Local dining", cards.get(4)),
                new CardBenefit(BenefitType.POINTS, 1.0, "All other purchases", cards.get(4))
        ));

    }

    public void saveUsers() {
        // encode password after adding security dependency
        userRepository.saveAll(Arrays.asList(
            new User("Alvin", "alvin@fdmgroup.com", "password"),
            new User("Theodore", "theodore@fdmgroup.com", "password"),
            new User("Simon", "simon@fdmgroup.com", "password")
            ));
    }

    public void saveUserCards() {

        // find all users
        List<User> users = userRepository.findAll();

        // find all cards
        List<Card> cards = cardRepository.findAll();

        userCardRepository.saveAll(Arrays.asList(
            new UserCard("4242 4242 4242 4242", LocalDateTime.of(2027, Month.AUGUST, 12, 0, 0), users.get((int) (Math.random() * users.size())), cards.get((int) (Math.random() * cards.size()))),
            new UserCard("4545 4545 4545 4545", LocalDateTime.of(2027, Month.APRIL, 23, 0, 0), users.get((int) (Math.random() * users.size())), cards.get((int) (Math.random() * cards.size()))),
            new UserCard("4040 4141 4242 4343", LocalDateTime.of(2028, Month.FEBRUARY, 20, 0, 0), users.get((int) (Math.random() * users.size())), cards.get((int) (Math.random() * cards.size()))),
            new UserCard("5252 5252 5252 5252", LocalDateTime.of(2029, Month.JANUARY, 17, 0, 0), users.get((int) (Math.random() * users.size())), cards.get((int) (Math.random() * cards.size())))
        ));
    }

}
