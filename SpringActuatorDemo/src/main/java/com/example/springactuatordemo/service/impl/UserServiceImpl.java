package com.example.springactuatordemo.service.impl;

import com.example.springactuatordemo.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public String getHomepage(String token) {
        return "asd";
    }
}