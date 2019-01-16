package com.wildBirds.BlueChat.domain.model;

import com.wildBirds.BlueChat.api.rest.dto.MessageDto;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class MessageRepositoryTest extends ConfigurationTest {


    private Logger logger = LoggerFactory.getLogger(MessageRepositoryTest.class);

    @Test
    public void shouldSaveMessage() {
        logger.info("Running test >> shouldSaveMessage");
        //given
        User sender = new User();
        sender.setNick("igorMessage");
        sender.setPassword("password");

        User receiver = new User();
        receiver.setNick("pawelMessage");
        receiver.setPassword("password");

        String content = "some message";
        Instant sentDate = Instant.now();

        Message message = new Message();
        message.setContent(content);
        message.setSentDate(sentDate);
        message.setSender(sender);
        message.setReceiver(receiver);
        //when

        Message messageSaved = messageRepository.save(message);

        //then
        Assert.assertEquals(content, messageSaved.getContent() );
        Assert.assertEquals(sentDate, messageSaved.getSentDate());
        Assert.assertEquals(sender.getNick(), messageSaved.getSender().getNick());
        Assert.assertEquals(receiver.getNick(), messageSaved.getReceiver().getNick());

    }

    @Test
    @Transactional
    public void shouldGetConversation() {

        //given
        User sender = new User();
        sender.setNick("igorMessage2");
        sender.setPassword("password");
        sender = userRepository.save(sender);

        User receiver = new User();
        receiver.setNick("pawelMessage2");
        receiver.setPassword("password");
        receiver = userRepository.save(receiver);

        String content1 = "some message11111111111111";
        Instant sentDate1 = Instant.now().plus(3, ChronoUnit.DAYS);

        String content2 = "some message222222222222";
        Instant sentDate2 = Instant.now().plus(1, ChronoUnit.DAYS);

        String content3 = "some message22222222222222";
        Instant sentDate3 = Instant.now().plus(2, ChronoUnit.DAYS);


        Message message1 = new Message();
        message1.setContent(content1);
        message1.setSentDate(sentDate1);
        message1.setSender(sender);
        message1.setReceiver(receiver);
        messageRepository.save(message1);

        Message message2 = new Message();
        message2.setContent(content2);
        message2.setSentDate(sentDate2);
        message2.setSender(sender);
        message2.setReceiver(receiver);

        messageRepository.save(message2);

//
        Message message3 = new Message();
        message3.setContent(content3);
        message3.setSentDate(sentDate3);
        message3.setSender(sender);
        message3.setReceiver(receiver);

        messageRepository.save(message3);
        //when

        List<MessageDto> conversation = messageFacade.getConversation(sender.getIdUser(), receiver.getIdUser(), 100, 0);


        conversation.stream()
                .forEach(messageDto -> System.out.println(messageDto));

        //then


    }

}