package com.shop.coffee.repository;

import com.shop.coffee.model.Purchase;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

@Repository
public interface PurchaseRepository extends MongoRepository<Purchase, String> {
    List<Purchase> findByUsername(String username);

    Purchase findByUsernameAndPurchaseCode(String username, String purchaseCode);

}
