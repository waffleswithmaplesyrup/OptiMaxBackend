package com.fdmgroup.optimax.Model;

import com.fdmgroup.optimax.ENUM.Bank;
import com.fdmgroup.optimax.ENUM.Issuer;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExistingCard {

    @Id
    private String cardNumber;
    private Bank bank;
    private Issuer issuer;
}
