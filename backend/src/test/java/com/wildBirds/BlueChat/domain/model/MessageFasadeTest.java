package com.wildBirds.BlueChat.domain.model;


import com.wildBirds.BlueChat.api.rest.dto.MessageDto;
import org.junit.Assert;
import org.junit.Test;

import java.time.Instant;

public class MessageFasadeTest extends ConfigurationTest {

    @Test
    public void shouldSaveMessage(){


        //before
        User sender = new User();
        sender.setNick("igorMessage");
        sender.setPassword("password");
        sender = userRepository.save(sender);

        User receiver = new User();
        receiver.setNick("pawelMessage");
        receiver.setPassword("password");
        receiver = userRepository.save(receiver);

        //given
        Long idReceiver = receiver.getIdUser();
        Long idSender = sender.getIdUser();
        String content = "content";
        Instant sentDate = Instant.now();

        //when

        MessageDto messageDto = new MessageDto(null, content, sentDate, idSender, idReceiver);
        MessageDto messageSaved = messageFacade.saveMessage(messageDto);

        //then
        Assert.assertEquals(Long.valueOf(1), messageSaved.getIdMessage());
        Assert.assertEquals(content, messageSaved.getContent() );
        Assert.assertEquals(sentDate, messageSaved.getSentDate());
        Assert.assertEquals(Long.valueOf(1), messageSaved.getSenderId());
        Assert.assertEquals(Long.valueOf(2), messageSaved.getReceiverId());
    }
}
