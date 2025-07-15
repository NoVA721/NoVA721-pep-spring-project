package com.example.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    AccountRepository accountRepository;
    //middle man, conditional stuff
    @Autowired
    public AccountService(AccountRepository accountRepository)
    {
        this.accountRepository = accountRepository;
    }

    public Account persistAccount(Account account)
    {
        return accountRepository.save(account);
    }

    public List<Account> getAllAccount()
    {
        return accountRepository.findAll();
    }

    public int getAccountByUsername(Account account)
    {
        if(!(account.getUsername() != "" && account.getPassword().length() >= 4)) //if it fails one of these. it's written backwards because thats how I first wrote it.
        {
            return 400;
        }
        if( (accountRepository.findByUsername(account.getUsername() ) ).isPresent()) //the account repository already exists.
        {
            return 409;
        }
        return 200;
    }

    public Account loginAttempt(Account account)
    {
        Optional<Account> optionalAccount = accountRepository.findByUsername(account.getUsername());
        if(optionalAccount.isPresent()) //if the username exists
        {
            if(account.getPassword().equals(optionalAccount.get().getPassword()))
            {
                //we are good
                return optionalAccount.get();
            }
        }
        return null;
    }

    public boolean getAccountById(Integer id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        return (optionalAccount.isPresent()); //returns true if the account id exists, false if it does not.
    }
}
