package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    List<Message> getMessagesByPostedBy(int postedBy); //this should be a @query which gets all messages by who they were posted by. keyword is should.
}
