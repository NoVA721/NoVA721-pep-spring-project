package com.example.service;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.repository.MessageRepository;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository)
    {
        this.messageRepository = messageRepository;
    }

    //get message by its messageID, return message
    public Message getMessageByMessageId(Integer messageId)
    {
        Optional<Message> optionalMessage = messageRepository.findById(messageId); //findById is for primary key
        if(optionalMessage.isPresent())
        {
            return optionalMessage.get();
        }
        else
        {
            return null;
        }
    }

    //get all messages
    public List<Message> getAllMessages()
    {
        return messageRepository.findAll();
    }

    //delete message by message id, return number of rows deleted. This is probably the silliest way to do this, and probably incorrect.
    public int deleteMessageByMessageId(Integer messageId)
    {
        Optional<Message> optionalMessage = messageRepository.findById(messageId); //findById is for primary key
        if(optionalMessage.isPresent())
        {
            messageRepository.deleteById(messageId);
            return 1;
        }
        return 0;
    }
}
