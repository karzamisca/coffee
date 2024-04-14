package com.shop.coffee.controller;

import com.shop.coffee.model.AdminCredential;
import com.shop.coffee.repository.AdminCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admincredentials")
public class AdminCredentialController {
    @Autowired
    private AdminCredentialRepository AdminCredentialRepository;

    @GetMapping
    public List<AdminCredential> getAllCoffees() {
        return AdminCredentialRepository.findAll();
    }

    @PostMapping
    public AdminCredential addCoffee(@RequestBody AdminCredential admincredential) {
        return AdminCredentialRepository.save(admincredential);
    }

    @PutMapping("/{id}")
    public AdminCredential updateCoffee(@PathVariable String id, @RequestBody AdminCredential admincredential) {
        return AdminCredentialRepository.save(admincredential);
    }

    @DeleteMapping("/{id}")
    public void deleteCoffee(@PathVariable String id) {
        AdminCredentialRepository.deleteById(id);
    }
}
