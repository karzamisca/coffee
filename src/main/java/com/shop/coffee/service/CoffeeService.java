package com.shop.coffee.service;

import com.shop.coffee.model.Coffee;
import com.shop.coffee.repository.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CoffeeService {

    @Autowired
    private CoffeeRepository coffeeRepository;

    public List<Coffee> getAllCoffees() {
        return coffeeRepository.findAll();
    }

    public Coffee getCoffeeById(String id) {
        return coffeeRepository.findById(id).orElse(null);
    }

    public Coffee addCoffee(Coffee coffee) {
        return coffeeRepository.save(coffee);
    }

    public Coffee updateCoffee(String id, Coffee coffee) {
        coffee.setId(id); // Ensure the ID is set for update
        return coffeeRepository.save(coffee);
    }

    public void deleteCoffee(String id) {
        coffeeRepository.deleteById(id);
    }

    public Coffee updateCoffee(String id, Map<String, Object> updates) {
        Coffee coffee = coffeeRepository.findById(id).orElse(null);
        if (coffee == null) {
            // Handle coffee not found
            return null;
        }

        updates.forEach((key, value) -> {
            switch (key) {
                case "title":
                    coffee.setTitle((String) value);
                    break;
                case "price":
                    coffee.setPrice((String) value);
                    break;
                case "image":
                    coffee.setImage((String) value);
                    break;
                case "quantityInStorage":
                    coffee.setQuantityInStorage((Integer) value);
                    break;
                // Add cases for other fields if needed
            }
        });

        return coffeeRepository.save(coffee);
    }
}
