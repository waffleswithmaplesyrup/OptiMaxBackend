package com.fdmgroup.optimax.Service;

import com.fdmgroup.optimax.DTO.UserCardRequest;
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
