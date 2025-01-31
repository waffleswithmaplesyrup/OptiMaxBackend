package com.fdmgroup.optimax.Service;

import com.fdmgroup.optimax.Model.UserCard;
import com.fdmgroup.optimax.Repository.UserCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCardService {

    private final UserCardRepository userCardRepository;

    @Autowired
    public UserCardService(UserCardRepository userCardRepository) {
        this.userCardRepository = userCardRepository;
    }

    public List<UserCard> getAllCardsByUser(int userId) {
        return userCardRepository.findAllByUser_UserId(userId);
    }
}
