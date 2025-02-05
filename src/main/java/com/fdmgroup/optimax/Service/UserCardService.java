package com.fdmgroup.optimax.Service;

import com.fdmgroup.optimax.DTO.UserCardRequest;
import com.fdmgroup.optimax.ENUM.Issuer;
import com.fdmgroup.optimax.Model.Card;
import com.fdmgroup.optimax.Model.User;
import com.fdmgroup.optimax.Model.UserCard;
import com.fdmgroup.optimax.Repository.CardRepository;
import com.fdmgroup.optimax.Repository.UserCardRepository;
import com.fdmgroup.optimax.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class UserCardService {

    private final UserCardRepository userCardRepository;
    private final UserRepository userRepository;
    private final CardRepository cardRepository;

    @Autowired
    public UserCardService(
            UserCardRepository userCardRepository,
            UserRepository userRepository,
            CardRepository cardRepository
    ) {
        this.userCardRepository = userCardRepository;
        this.userRepository = userRepository;
        this.cardRepository = cardRepository;
    }

    public List<UserCard> getAllCardsByUser(int userId) {
        return userCardRepository.findAllByUser_UserId(userId);
    }

    public Issuer getIssuerDuringOnboarding(String cardNumber) {

        // check if the cardNumber is empty or less than 1
        if (cardNumber.isEmpty()) throw new RuntimeException();
        // make sure to validate in the frontend so this exception isn't thrown

        // convert cardNumber into an array
        int[] arrOfInt = Stream.of(cardNumber.split(""))
                .mapToInt(Integer::parseInt)
                .toArray();

        // check the first digit, if it's 4, it's VISA
        if (arrOfInt[0] == 4) return Issuer.VISA;

        // if the first two digits, converted into int,
        // is a value btw 51 - 55, it's MASTERCARD
        if (arrOfInt[0] == 5 && (arrOfInt[1] <= 5 && arrOfInt[1] >= 1)) return Issuer.MASTERCARD;

        // first four digits
        int[] subArr = IntStream.range(0, 4).map(i -> arrOfInt[i]).toArray();

        // convert subArr into an integer with 4 digits
        int result = 0;
        for (int i = 0; i < subArr.length; i++) {
            result = result * 10 + subArr[i];
        }

        if (result >= 2221 && result <= 2720) {
            // if the first 4 digits, converted into int,
            // is a value btw 2221 - 2720, it's MASTERCARD
            return Issuer.MASTERCARD;
        } else {
            throw new NoSuchElementException();
        }
    }

    public UserCard onboardNewCard(UserCardRequest userCardRequest) {

        // find user in db
        Optional<User> userInDB = userRepository.findById(userCardRequest.getUserId());

        if (userInDB.isEmpty()) throw new NoSuchElementException();

        // find card in db
        Optional<Card> cardInDB = cardRepository.findById(userCardRequest.getCardId());

        if (cardInDB.isEmpty()) throw new NoSuchElementException();

        UserCard newCard = new UserCard(
                userCardRequest.getCardNumber(),
                userCardRequest.getExpiryDate(),
                userCardRequest.getRewardsEarned(),
                userInDB.get(),
                cardInDB.get()
        );

        return userCardRepository.save(newCard);
    }
}
