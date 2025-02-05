package com.fdmgroup.optimax;

import com.fdmgroup.optimax.Utils.DatabaseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

@Service
public class DatabaseLoader implements ApplicationRunner {

    private final DatabaseHelper databaseHelper;

    @Autowired
    public DatabaseLoader(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        // save cards to db
        databaseHelper.saveCardTypes();

        // save card benefits to db
        databaseHelper.saveCardBenefits();

        // save users to db
        databaseHelper.saveUsers();

        // save user cards to db
        databaseHelper.saveUserCards();

        // save existing cards to db
        databaseHelper.saveExistingCards();

    }
}
