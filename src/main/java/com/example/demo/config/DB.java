package com.example.demo.config;

import com.example.demo.model.Merchant;
import com.example.demo.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DB {
    public final List<Merchant> merchants = new ArrayList<>();
    public final List<User> users = new ArrayList<>();

    public User userGetById(Long id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Merchant merchantGetById(Long id) {
        return merchants.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
