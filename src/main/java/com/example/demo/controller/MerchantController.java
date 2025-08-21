package com.example.demo.controller;

import com.example.demo.input.MerchantInput;
import com.example.demo.model.Merchant;
import com.example.demo.service.MerchantService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MerchantController {
    private final MerchantService service;

    @QueryMapping
    public List<Merchant> merchants() {
        return service.getAll();
    }

    @QueryMapping
    public Merchant merchantById(@Argument Long id) {
        return service.getById(id);
    }

    @MutationMapping
    public Merchant addMerchant(@Argument MerchantInput merchant) {
        return service.create(merchant);
    }

}
