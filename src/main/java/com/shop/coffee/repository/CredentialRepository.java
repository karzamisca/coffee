package com.shop.coffee.repository;

import com.shop.coffee.model.Credential;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CredentialRepository extends MongoRepository<Credential, String> {
}
