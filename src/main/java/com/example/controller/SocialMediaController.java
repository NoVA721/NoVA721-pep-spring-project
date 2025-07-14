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

    /*@PostMapping("/register") //this is how I make endpoints. so it'd be @PostMapping, @GetMapping, etc
    public ResponseEntity<Account> register(@RequestBody Account body)
    {
        //ResponseEntity<Account> resEnt = template.getForEntity
        //Fuck it, i'm going to try to test with user and password.
        //if(user != "" && pass.length() >= 4)
        if(body.getUsername() != "" && body.getPassword().length() >= 4)
        {
            if(accountService.getAccountByUsername(body.getUsername()) != null) //so if there is an account by this username, we need to fail it with code 409.
            {
                //so what the hell do we shit out of this. I still don't knw how THIS works. I'm going insane.
                //ResponseEntity<Account> test = new ResponseEntity<>(body, HttpStatus.CONFLICT);
                //body.status(409); //nah this doesn't work.
                //ResponseEntity.status(HttpStatus.CONFLICT); //will this work? At all? Something is giving me problems.
                //return null; //I think this will... maybe... work. Probably not this isn't being put anywhere WHAT DO I DO.
                ResponseEntity<Account> test = new ResponseEntity<>(body, HttpStatus.CONFLICT); //the body won't work, need a function in accountService to get full account
                return test;
            }
            else
            {
                //below is just me testing out creating a new response entity.
                ResponseEntity<Account> test = new ResponseEntity<>(body, HttpStatus.CREATED); //so this technically works. I don't know if it has the proper ID though. Maybe??
                //return accountService.persistAccount(test);
                return test; //oh wait i probably need to save it too 
                //return null;
            }
        }
        else
        {
            //ResponseEntity.status(HttpStatus.BAD_REQUEST);
            //return null;
            ResponseEntity<Account> test = new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
            return test;
        }
    }*/

    /*@PostMapping("/register")
    public ResponseEntity<Account> register(@RequestBody Account body)
    {
        return null;
    }*/

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
        //this needs to return a response entity with the body having however many were deleted if any were, and empty if none were. how in the god damn. I guess just have the messageService.whatever return the number of rows affected, and if 0 then have it be empty.
        if(messageService.deleteMessageByMessageId(messageId) == 1)
        {
            return ResponseEntity.status(200).body("1");
        }
        else
        {
            return ResponseEntity.status(200).body("");
        }
    }

    //create a new message. This requires me to be able to find users. Last one I can do without touching Accounts is updating via the messageId.
   /*@PostMapping("messages")
    public ResponseEntity<Message>
    {

    }*/

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
}
