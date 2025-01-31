package com.fdmgroup.optimax.DTO;

import com.fdmgroup.optimax.Model.Card;
import com.fdmgroup.optimax.Model.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserCardRequest {

    private String cardNumber;
    private LocalDateTime expiryDate;
    private Double rewardsEarned;
    private int userId;
    private int cardId;
}