package com.fdmgroup.optimax.Repository;

import com.fdmgroup.optimax.Model.ExistingCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExistingCardRepository extends JpaRepository<ExistingCard, String> {
}
