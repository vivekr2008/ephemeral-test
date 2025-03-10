package com.ephemeral.citest.controller;

import com.ephemeral.citest.entity.AccountDetails;
import com.ephemeral.citest.repository.AccountDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accountDetails")
public class AccountDetailsController {

    @Autowired
    private AccountDetailsRepository accountDetailsRepository;

    @PostMapping
    public AccountDetails createUser(@RequestBody AccountDetails user) {
        return accountDetailsRepository.save(user);
    }

    @GetMapping
    public List<AccountDetails> getAllUsers() {
        return accountDetailsRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<AccountDetails> getUserById(@PathVariable Long id) {
        return accountDetailsRepository.findById(id);
    }

    @PutMapping("/{id}")
    public AccountDetails updateUser(@PathVariable Long id, @RequestBody AccountDetails user) {
        user.setId(id);
        return accountDetailsRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        accountDetailsRepository.deleteById(id);
    }
}
