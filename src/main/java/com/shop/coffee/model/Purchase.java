package com.shop.coffee.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "purchases")
public class Purchase {
    @Id
    public String _id;
    public String username;
    public String purchaseCode;
    public List<Item> items;
    public double totalPrice;
    public String status;
    public String purchaseDate;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPurchaseCode() {
        return purchaseCode;
    }

    public void setPurchaseCode(String purchaseCode) {
        this.purchaseCode = purchaseCode;
    }
}

class Item {
    public String productId;
    public String title;
    public int quantity;
    public double price;
}
