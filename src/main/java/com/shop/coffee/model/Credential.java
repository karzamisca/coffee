package com.shop.coffee.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "credentials")
public class Credential {
    @Id
    public String _id;
    public String password;
    public String username;
}