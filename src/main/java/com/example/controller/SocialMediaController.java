package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
//need to use @RestController
//this doesn't use beans, supposedly.
@RestController
public class SocialMediaController {
    AccountService accountService;
    MessageService messageService;
    
    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService)
    {
        this.accountService = accountService;
        this.messageService = messageService;
    }

    //need to define every method here? @POST, @GET, etc.

    @PostMapping("/register")
    public ResponseEntity<Account> register(@RequestBody Account body)
    {
        int response = 0;
        if(accountService.getAccountByUsername(body) == 400)
        {
            response = 400;
        }
        else if (accountService.getAccountByUsername(body) == 409)
        {
            response = 409;
        }
        else if (accountService.getAccountByUsername(body) == 200)
        {
            response = 200;
            accountService.persistAccount(body);
        }
        return ResponseEntity.status(response).body(body);
    }

    @GetMapping("messages/{messageId}")
    public ResponseEntity<Message> findMessageByMessageId(@PathVariable Integer messageId)
    {
        return ResponseEntity.status(200).body(messageService.getMessageByMessageId(messageId));
    } 

    //get all messages
    @GetMapping("messages")
    public ResponseEntity<List<Message>> getAllMessages()
    {
        return ResponseEntity.status(200).body(messageService.getAllMessages());
    }
    
    //delete message by its ID
    @DeleteMapping("messages/{messageId}")
    public ResponseEntity<String> deleteMessageByMessageId(@PathVariable Integer messageId)
    {
        //return null;
        //this needs to return a response entity with the body having however many were deleted if any were, and empty if none were.
        if(messageService.deleteMessageByMessageId(messageId) == 1)
        {
            return ResponseEntity.status(200).body("1");
        }
        else
        {
            return ResponseEntity.status(200).body("");
        }
    }

    
   @PostMapping("messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message message)
    {
        int response = 400; //starting this at 400 in place of having an else on the outside if to set it to 400.
        if(accountService.getAccountById(message.getPostedBy()))
        {
            //if we are here, then the ID is correct.
            if(messageService.addMessage(message) == 200)
            {
                response = 200;
                messageService.persistMessage(message);
            }
            else
            {
                response = 400;
            }
        }
        return ResponseEntity.status(response).body(message);
    }

    //update message via messageid
    @PatchMapping("messages/{messageId}")
    public ResponseEntity<String> updateMessageByMessageId(@PathVariable Integer messageId, @RequestBody Message updateMessage)
    {
        if(messageService.updateMessageByMessageId(messageId, updateMessage.getMessageText()) == 1)
        {
            return ResponseEntity.status(200).body("1");
        }
        else
        {
            return ResponseEntity.status(400).body("");
        }
    }

    @PostMapping("login")
    public ResponseEntity<Account> loginAttempt(@RequestBody Account account)
    {
        int response = 0;
        if(accountService.loginAttempt(account) == null)
        {
            response = 401;
            return ResponseEntity.status(response).body(null);
        }
        else
        {
            response = 200;
            return ResponseEntity.status(response).body(accountService.loginAttempt(account));
        }
    }

    @GetMapping("accounts/{accountId}/messages")
    public ResponseEntity<List<Message>> getAllMessagesByAccountId(@PathVariable Integer accountId)
    {
        //thinking I might need to do an if(getaccountbyid, real account) but I can also just... try yeeting in the call to getMessagePostedBy. Yeah that works lol.
        return ResponseEntity.status(200).body(messageService.getAllMessagesByPostedBy(accountId));
        //return ResponseEntity.status(200).body(null);
    }

}
