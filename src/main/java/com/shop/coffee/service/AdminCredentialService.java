package com.shop.coffee.service;

import com.shop.coffee.model.AdminCredential;
import com.shop.coffee.repository.AdminCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminCredentialService {

    @Autowired
    private AdminCredentialRepository adminCredentialRepository;

    public List<AdminCredential> getAllAdminCredentials() {
        return adminCredentialRepository.findAll();
    }

    public AdminCredential addAdminCredential(AdminCredential adminCredential) {
        return adminCredentialRepository.save(adminCredential);
    }

    public AdminCredential updateAdminCredential(String id, AdminCredential adminCredential) {
        return adminCredentialRepository.save(adminCredential);
    }

    public void deleteAdminCredential(String id) {
        adminCredentialRepository.deleteById(id);
    }
}
