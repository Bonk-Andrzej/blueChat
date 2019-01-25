package com.wildBirds.BlueChat.domain.model;

import com.wildBirds.BlueChat.api.rest.dto.UserDtoWithLastMessage;
import org.junit.Assert;
import org.junit.Test;

import javax.transaction.Transactional;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class UserWithLasMessageFacadeTest extends ConfigurationTest {
    @Test
    @Transactional
    public void shouldGetConversation() {

        //given
        User sender = new User();
        sender.setNick("igorMessage2");
        sender.setPassword("password");
        sender = userRepository.save(sender);


        User sender2 = new User();
        sender2.setNick("igorMessage22");
        sender2.setPassword("password");
        sender2 = userRepository.save(sender2);

        User sender3 = new User();
        sender3.setNick("igorMessage222");
        sender3.setPassword("password");
        sender3 = userRepository.save(sender3);

        User receiver = new User();
        receiver.setNick("pawelMessage2222");
        receiver.setPassword("password");
        receiver = userRepository.save(receiver);

        String content1 = "some message13333";
        Instant sentDate1 = Instant.now().plus(3, ChronoUnit.DAYS);

        String content2 = "some message1111";
        Instant sentDate2 = Instant.now().plus(1, ChronoUnit.DAYS);

        String content3 = "some message2222";
        Instant sentDate3 = Instant.now().plus(2, ChronoUnit.DAYS);


        String content4 = "some message6666";
        Instant sentDate4 = Instant.now().plus(6, ChronoUnit.DAYS);


        String content5 = "some message444";
        Instant sentDate5 = Instant.now().plus(4, ChronoUnit.DAYS);


        String content6 = "some message777";
        Instant sentDate6 = Instant.now().plus(7, ChronoUnit.DAYS);

        String content7 = "some message555";
        Instant sentDate7 = Instant.now().plus(5, ChronoUnit.DAYS);

        Message message1 = new Message();
        message1.setContent(content1);
        message1.setSentDate(sentDate1);
        message1.setSender(sender);
        message1.setReceiver(receiver);
        message1.setRead(false);
        messageRepository.save(message1);

        Message message2 = new Message();
        message2.setContent(content2);
        message2.setSentDate(sentDate2);
        message2.setSender(sender);
        message2.setReceiver(receiver);
        message2.setRead(false);
        messageRepository.save(message2);

//
        Message message3 = new Message();
        message3.setContent(content3);
        message3.setSentDate(sentDate3);
        message3.setSender(sender2);
        message3.setReceiver(receiver);
        message3.setRead(false);
        messageRepository.save(message3);

        Message message4 = new Message();
        message4.setContent(content4);
        message4.setSentDate(sentDate4);
        message4.setSender(sender);
        message4.setReceiver(receiver);
        message4.setRead(false);
        messageRepository.save(message4);


        Message message5 = new Message();
        message5.setContent(content5);
        message5.setSentDate(sentDate5);
        message5.setSender(sender3);
        message5.setReceiver(receiver);
        message5.setRead(false);
        messageRepository.save(message5);


        Message message6 = new Message();
        message6.setContent(content6);
        message6.setSentDate(sentDate6);
        message6.setSender(sender);
        message6.setReceiver(receiver);
        message6.setRead(false);
        messageRepository.save(message6);


        Message message7 = new Message();
        message7.setContent(content7);
        message7.setSentDate(sentDate7);
        message7.setSender(sender2);
        message7.setReceiver(receiver);
        message7.setRead(false);
        messageRepository.save(message7);
        //when

        List<UserDtoWithLastMessage> firstNoReadMessages = userWithLasMessageFacade.getFirstNoReadMessages(receiver.getIdUser());
        //then

        Assert.assertEquals(3, firstNoReadMessages.size());


    }

}