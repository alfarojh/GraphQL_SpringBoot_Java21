package com.example.demo.controller;

import com.example.demo.input.UserInput;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @QueryMapping
    public List<User> users() {
        return service.getAll();
    }

    @QueryMapping
    public User userById(@Argument("id") String id) {
        return service.getById(Long.parseLong(id));
    }

    @MutationMapping
    public User addUser(@Argument("user") UserInput user) {
        return service.create(user);
    }

}
