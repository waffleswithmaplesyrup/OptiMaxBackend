package com.fdmgroup.optimax.Repository;

import com.fdmgroup.optimax.Model.UserCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCardRepository extends JpaRepository<UserCard, Integer> {
}
