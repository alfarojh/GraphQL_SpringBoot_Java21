package com.example.demo.service;

import com.example.demo.config.DB;
import com.example.demo.input.MerchantInput;
import com.example.demo.model.Merchant;
import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantService {
    private final DB db;

    private final List<Merchant> merchants;

    public MerchantService(DB db) {
        this.db = db;
        this.merchants = db.merchants;
    }

    public List<Merchant> getAll() {
        System.out.println(merchants.getFirst().getUser().getId());
        return merchants;
    }

    public Merchant getById(Long id) {
        return db.merchantGetById(id);
    }

    public Merchant create(MerchantInput merchantInput) {
        Merchant merchant = new Merchant();
        merchant.setId(merchants.size() + 1L);
        merchant.setName(merchantInput.getName());
        merchant.setEmail(merchantInput.getEmail());

        User user = db.userGetById(Long.parseLong(merchantInput.getUserId()));
        merchant.setUser(user);
        merchants.add(merchant);
        return merchant;
    }
}
