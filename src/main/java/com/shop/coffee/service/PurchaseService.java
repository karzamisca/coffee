package com.shop.coffee.service;

import com.shop.coffee.model.Purchase;
import com.shop.coffee.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    public ResponseEntity<List<Purchase>> getAllPurchases() {
        try {
            List<Purchase> purchases = purchaseRepository.findAll();
            return new ResponseEntity<>(purchases, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Purchase> createPurchase(Purchase purchase) {
        try {
            Purchase newPurchase = purchaseRepository.save(purchase);
            return new ResponseEntity<>(newPurchase, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Purchase>> getPurchasesByUsername(String username) {
        try {
            List<Purchase> purchases = purchaseRepository.findByUsername(username);
            return new ResponseEntity<>(purchases, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Purchase> getPurchaseDetails(String username, String purchaseCode) {
        try {
            Purchase purchase = purchaseRepository.findByUsernameAndPurchaseCode(username, purchaseCode);
            if (purchase == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(purchase, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Purchase> updatePurchaseField(String username, String purchaseCode,
            Map<String, Object> updates) {
        try {
            Purchase existingPurchase = purchaseRepository.findByUsernameAndPurchaseCode(username, purchaseCode);
            if (existingPurchase == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            updates.forEach((key, value) -> {
                switch (key) {
                    case "status":
                        existingPurchase.setStatus((String) value);
                        break;
                    case "username":
                        existingPurchase.setUsername((String) value);
                        break;
                    case "PurchaseCode":
                        existingPurchase.setPurchaseCode((String) value);
                        break;
                    // Add cases for other fields if needed
                }
            });

            Purchase updatedPurchase = purchaseRepository.save(existingPurchase);
            return new ResponseEntity<>(updatedPurchase, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
