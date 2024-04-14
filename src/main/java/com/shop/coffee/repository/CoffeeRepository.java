package com.shop.coffee.repository;

import com.shop.coffee.model.Coffee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CoffeeRepository extends MongoRepository<Coffee, String> {
}
