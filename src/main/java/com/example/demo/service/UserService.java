package com.example.demo.service;

import com.example.demo.config.DB;
import com.example.demo.input.UserInput;
import com.example.demo.model.Merchant;
import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final DB db;

    private Long counter = 1L;
    private final List<User> users;

    public UserService(DB db) {
        this.db = db;
        this.users = db.users;
    }

    public List<User> getAll() {
        return users;
    }

    public User getById(Long id) {
        return db.userGetById(id);
    }

    public User create(UserInput userInput) {
        User user = new User();
        user.setId(counter++);
        user.setName(userInput.getName());
        user.setEmail(userInput.getEmail());

        Merchant merchant = db.merchantGetById(Long.parseLong(userInput.getMerchantId()));
        user.setMerchant(merchant);
        users.add(user);
        return user;
    }
}
