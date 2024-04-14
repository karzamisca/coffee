package com.shop.coffee.controller;

import com.shop.coffee.model.Credential;
import com.shop.coffee.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/credentials")
public class CredentialController {

    @Autowired
    private CredentialService credentialService;

    @GetMapping
    public List<Credential> getAllCredentials() {
        return credentialService.getAllCredentials();
    }

    @PostMapping
    public Credential addCredential(@RequestBody Credential credential) {
        return credentialService.addCredential(credential);
    }

    @PutMapping("/{id}")
    public Credential updateCredential(@PathVariable String id, @RequestBody Credential credential) {
        return credentialService.updateCredential(id, credential);
    }

    @DeleteMapping("/{id}")
    public void deleteCredential(@PathVariable String id) {
        credentialService.deleteCredential(id);
    }
}
