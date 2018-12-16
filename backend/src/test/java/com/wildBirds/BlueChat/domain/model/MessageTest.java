package com.wildBirds.BlueChat.domain.model;

import com.wildBirds.BlueChat.api.rest.dto.MessageDto;
import org.junit.Assert;
import org.junit.Test;

import javax.transaction.Transactional;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class MessageTest extends ConfigurationTest {

//    @Autowired
//    MessageRepository messageRepository;


    @Test
    public void shouldSaveMessage() {

        //given
        User sender = new User();
        sender.setNick("igorMessage");
        sender.setPassword("password");

        User receiver = new User();
        receiver.setNick("pawelMessage");
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


        Message message1 = new Message(null, content1, sentDate1, sender, receiver);
        messageRepository.save(message1);

        Message message2 = new Message(null, content2, sentDate2, receiver, sender);

        messageRepository.save(message2);

//
        Message message3 = new Message(null, content3, sentDate3, sender, receiver);

        messageRepository.save(message3);
        //when

        List<MessageDto> conversation = messageFacade.getConversation(sender.getIdUser(), receiver.getIdUser(), 100, 0);


        conversation.stream()
                .forEach(messageDto -> System.out.println(messageDto));

        //then


    }

}