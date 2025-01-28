package com.fdmgroup.optimax.Service;

import com.fdmgroup.optimax.Repository.UserCardRepository;
import com.fdmgroup.optimax.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCardService {

    private final UserCardRepository userCardRepository;

    @Autowired
    public UserCardService(UserCardRepository userCardRepository) {
        this.userCardRepository = userCardRepository;
    }
}
