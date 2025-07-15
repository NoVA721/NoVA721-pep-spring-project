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

    public Message persistMessage(Message message)
    {
        return messageRepository.save(message);
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

    //delete message by message id, return number of rows deleted. This is probably the silliest way to do this, and probably incorrect in some capacity.
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

    //update message by message ID, return number of rows edited for the response body or... be empty, I guess, for a failure? another one like this that trips me up for the return.
    //i get the feeling I'm doing this hella wrong.
    public int updateMessageByMessageId(Integer messageId, String updatedMessage)
    {
        //we need to check if messageId is real, if updatedMessage is not blank and if it's not over 255 characters. If not, it fails, so... we return... 0...
        Optional<Message> optionalMessage = messageRepository.findById(messageId); //findById is for primary key
        if(optionalMessage.isPresent() && updatedMessage != "" && updatedMessage.length() <= 255) //if it meets all 3 of these, than it's a valid update.
        {
            optionalMessage.get().setMessageText(updatedMessage); //hopefully this alone will update the text?
            return 1;
        }
        else
        {
            return 0;
        }
    }

    public int addMessage(Message message)
    {
        //I need some way to check if the postedBy is a real userId. So, I think.. I'll need to do the check in the Controller. That makes sense to me.
        if(message.getMessageText() != "" && message.getMessageText().length() <= 255)
        {
            //everything is valid, so we can save the message. However, we're just going to return the http code, because I know how to do things that way.
            return 200;
        }
        return 400;
    }

    public List<Message> getAllMessagesByPostedBy(int postedBy)
    {
        return messageRepository.getMessagesByPostedBy(postedBy);
    }
}
