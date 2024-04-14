package com.shop.coffee.repository;

import com.shop.coffee.model.AdminCredential;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdminCredentialRepository extends MongoRepository<AdminCredential, String> {
}
