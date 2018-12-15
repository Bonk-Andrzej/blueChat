package com.wildBirds.BlueChat.domain.model;

import org.junit.Assert;
import org.junit.Test;

import java.time.Instant;

public class MessageTest {

    MessageRepository messageRepository;


    @Test
    public void shouldSaveMessage() {

        //given
        User sender = new User();
        sender.setNick("igor");
        sender.setPassword("password");

        User receiver = new User();
        receiver.setNick("pawel");
        receiver.setPassword("password");

        String content = "some message";
        Instant sentDate = Instant.now();

        Message message = new Message(null, content, sentDate, sender, receiver);

        //when

        Message messageSaved = messageRepository.save(message);

        //then
        Assert.assertEquals(content, messageSaved.getContent() );
        Assert.assertEquals(sentDate, messageSaved.getSentDate());
        Assert.assertEquals(sender.getNick(), messageSaved.getSender().getNick());
        Assert.assertEquals(receiver.getNick(), messageSaved.getReceiver().getNick());

    }

}