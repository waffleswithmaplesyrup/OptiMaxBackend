package com.fdmgroup.optimax.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCard {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UserCard_generator")
    @SequenceGenerator(name = "UserCard_generator", sequenceName = "UserCard_seq", allocationSize = 1)
    private int userCardId;
    private String cardNumber;
    private LocalDateTime expiryDate;

    @ManyToOne
    @JoinColumn(name = "FK_userId")
    private User user;
    @ManyToOne
    @JoinColumn(name = "FK_cardId")
    private Card card;

    public UserCard(String cardNumber, LocalDateTime expiryDate, User user, Card card) {
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.user = user;
        this.card = card;
    }
}
