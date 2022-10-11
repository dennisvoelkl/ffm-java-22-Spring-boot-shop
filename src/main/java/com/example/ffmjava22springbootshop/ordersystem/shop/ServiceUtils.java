package com.example.ffmjava22springbootshop.ordersystem.shop;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ServiceUtils {
    public String generateUUID(){
        return UUID.randomUUID().toString();
    }
}
