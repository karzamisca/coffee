package com.shop.coffee.controller;

import com.shop.coffee.model.Coffee;
import com.shop.coffee.service.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/coffees")
public class CoffeeController {

    @Autowired
    private CoffeeService coffeeService;

    @GetMapping
    public List<Coffee> getAllCoffees() {
        return coffeeService.getAllCoffees();
    }

    @GetMapping("/{id}")
    public Coffee getCoffeeById(@PathVariable String id) {
        return coffeeService.getCoffeeById(id);
    }

    @PostMapping
    public Coffee addCoffee(@RequestBody Coffee coffee) {
        return coffeeService.addCoffee(coffee);
    }

    @PutMapping("/{id}")
    public Coffee updateCoffee(@PathVariable String id, @RequestBody Coffee coffee) {
        return coffeeService.updateCoffee(id, coffee);
    }

    @DeleteMapping("/{id}")
    public void deleteCoffee(@PathVariable String id) {
        coffeeService.deleteCoffee(id);
    }

    @PatchMapping("/{id}")
    public Coffee updateCoffee(@PathVariable String id, @RequestBody Map<String, Object> updates) {
        return coffeeService.updateCoffee(id, updates);
    }
}
