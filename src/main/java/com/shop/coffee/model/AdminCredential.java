package com.shop.coffee.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "admincredentials")
public class AdminCredential {
    @Id
    public String _id;
    public String password;
    public String username;
}