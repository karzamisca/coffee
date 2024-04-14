package com.shop.coffee.controller;

import com.shop.coffee.model.Purchase;
import com.shop.coffee.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping
    public ResponseEntity<List<Purchase>> getAllPurchases() {
        return purchaseService.getAllPurchases();
    }

    @PostMapping
    public ResponseEntity<Purchase> createPurchase(@RequestBody Purchase purchase) {
        return purchaseService.createPurchase(purchase);
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<Purchase>> getPurchasesByUsername(@PathVariable String username) {
        return purchaseService.getPurchasesByUsername(username);
    }

    @GetMapping("/{username}/{purchaseCode}")
    public ResponseEntity<Purchase> getPurchaseDetails(@PathVariable String username,
            @PathVariable String purchaseCode) {
        return purchaseService.getPurchaseDetails(username, purchaseCode);
    }

    @PatchMapping("/{username}/{purchaseCode}")
    public ResponseEntity<Purchase> updatePurchaseField(@PathVariable String username,
            @PathVariable String purchaseCode, @RequestBody Map<String, Object> updates) {
        return purchaseService.updatePurchaseField(username, purchaseCode, updates);
    }
}
