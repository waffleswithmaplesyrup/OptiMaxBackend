package com.fdmgroup.optimax.Repository;

import com.fdmgroup.optimax.Model.CardBenefit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardBenefitRepository extends JpaRepository<CardBenefit, Integer> {
}
