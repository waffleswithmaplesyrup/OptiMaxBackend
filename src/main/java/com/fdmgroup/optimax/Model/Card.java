package com.fdmgroup.optimax.Model;

import com.fdmgroup.optimax.ENUM.Bank;
import com.fdmgroup.optimax.ENUM.Issuer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Card_generator")
    @SequenceGenerator(name = "Card_generator", sequenceName = "Card_seq", allocationSize = 1)
    private int cardId;
    @Enumerated(EnumType.STRING)
    private Issuer issuer;
    @Enumerated(EnumType.STRING)
    private Bank bank;
    @Column(unique = true)
    private String name;
    private String image;

    public Card(Issuer issuer, Bank bank, String name, String image) {
        this.issuer = issuer;
        this.bank = bank;
        this.name = name;
        this.image = image;
    }
}
