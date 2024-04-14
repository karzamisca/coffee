package com.shop.coffee.service;

import com.shop.coffee.model.Credential;
import com.shop.coffee.repository.CredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {

    @Autowired
    private CredentialRepository credentialRepository;

    public List<Credential> getAllCredentials() {
        return credentialRepository.findAll();
    }

    public Credential addCredential(Credential credential) {
        return credentialRepository.save(credential);
    }

    public Credential updateCredential(String id, Credential credential) {
        return credentialRepository.save(credential);
    }

    public void deleteCredential(String id) {
        credentialRepository.deleteById(id);
    }
}
