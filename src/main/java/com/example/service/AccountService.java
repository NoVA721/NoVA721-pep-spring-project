package com.example.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
   /*  AccountRepository accountRepository;
    //middle man, conditional stuff
    @Autowired
    public AccountService(AccountRepository accountRepository)
    {
        this.accountRepository = accountRepository;
    }


    //
    public Account persistAccount(Account account)
    {
        return accountRepository.save(account);
    }

    public List<Account> getAllAccount()
    {
        return accountRepository.findAll();
    }*/

    /*public Account getAccountByUsername(String user) {
        Optional<Account> optionalAccount = accountRepository.findByUsername(user);
        if(optionalAccount.isPresent())
        {
            return optionalAccount.get();
        }
        else
        {
            return null;
        }
    }*/
}
