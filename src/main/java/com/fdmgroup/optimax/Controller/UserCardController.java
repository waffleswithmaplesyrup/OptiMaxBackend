package com.fdmgroup.optimax.Controller;

import com.fdmgroup.optimax.DTO.UserCardRequest;
import com.fdmgroup.optimax.ENUM.Issuer;
import com.fdmgroup.optimax.Model.UserCard;
import com.fdmgroup.optimax.Service.UserCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserCardController {

    private final UserCardService userCardService;

    @Autowired
    public UserCardController(UserCardService userCardService) {
        this.userCardService = userCardService;
    }

    @QueryMapping
    public List<UserCard> getAllCardsByUser(@Argument int userId) {
        return userCardService.getAllCardsByUser(userId);
    }

    @QueryMapping
    public Issuer getIssuerDuringOnboarding(@Argument String cardNumber) {
        return userCardService.getIssuerDuringOnboarding(cardNumber);
    }

    @MutationMapping
    public UserCard onboardNewCard(@Argument UserCardRequest userCardRequest) {
        return userCardService.onboardNewCard(userCardRequest);
    }
}
