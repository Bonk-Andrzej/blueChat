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

    @Test
    @Transactional
    public void getLastMessage() {

        //given
        User sender = new User();
        sender.setNick("igorMessage2");
        sender.setPassword("password");
        sender = userRepository.save(sender);

        User receiver = new User();
        receiver.setNick("pawelMessage2");
        receiver.setPassword("password");
        receiver = userRepository.save(receiver);

        String content1 = "stara";
        Instant sentDate1 = Instant.now().plus(1, ChronoUnit.DAYS);

        String content2 = "druga";
        Instant sentDate2 = Instant.now().plus(2, ChronoUnit.DAYS);

        String content3 = "najmlodsza";
        Instant sentDate3 = Instant.now().plus(3, ChronoUnit.DAYS);


        Message message1 = new Message();
        message1.setContent(content1);
        message1.setSentDate(sentDate1);
        message1.setSender(sender);
        message1.setReceiver(receiver);
        messageRepository.save(message1);

        Message message2 = new Message();
        message2.setContent(content2);
        message2.setSentDate(sentDate2);
        message2.setSender(receiver);
        message2.setReceiver(sender);

        messageRepository.save(message2);

//
        Message message3 = new Message();
        message3.setContent(content3);
        message3.setSentDate(sentDate3);
        message3.setSender(sender);
        message3.setReceiver(receiver);

        messageRepository.save(message3);
        //when

        List<Object> objects = messageRepository.lastMessages(sender.getIdUser());

//        Set<LastMessageAggregate> set = new HashSet<>();
//
//                lastMessageAggregates.stream()
//                        .forEach(lastMessageAggregate -> set.add(lastMessageAggregate));
//
//        System.out.println(set);

        System.out.println("aaaa");
        //then


    }

    @Test
    @Transactional
    public void shouldReturnFriendsNoReadMessage(){
        //firstFriendship  --- 1
        User loggedUser = new User();
        loggedUser.setNick("loggedUserFriend2");
        loggedUser.setPassword("password");
        loggedUser.setPassword("somepassword");
        loggedUser = userRepository.save(loggedUser);

        User hisFriend1 = new User();
        hisFriend1.setNick("MarkIgorFriends32");
        hisFriend1.setPassword("somePass");
        hisFriend1 = userRepository.save(hisFriend1);

        UserContainFriend userContainFriend = new UserContainFriend();

        userContainFriend.setUser1(loggedUser);
        userContainFriend.setUser2(hisFriend1);
        userContainFriend.setDateFriendShip(Instant.now().plus(5, ChronoUnit.MINUTES));

        userContainFriendRepository.save(userContainFriend);

        //firstFriendship  --- 2

        User hisFriend2 = new User();
        hisFriend2.setNick("MarkIgorFriends42");
        hisFriend2.setPassword("somePass");
        hisFriend2 = userRepository.save(hisFriend2);

        UserContainFriend userContainFriend2 = new UserContainFriend();

        userContainFriend2.setUser1(loggedUser);
        userContainFriend2.setUser2(hisFriend2);
        userContainFriend2.setDateFriendShip(Instant.now().plus(10, ChronoUnit.MINUTES));

        userContainFriendRepository.save(userContainFriend2);

        //firstFriendship  --- 3

        User hisFriend3 = new User();
        hisFriend3.setNick("MarkIgorFriends52");
        hisFriend3.setPassword("somePass");
        hisFriend3 = userRepository.save(hisFriend3);

        UserContainFriend userContainFriend3 = new UserContainFriend();

        userContainFriend3.setUser1(hisFriend3);
        userContainFriend3.setUser2(loggedUser);
        userContainFriend3.setDateFriendShip(Instant.now().plus(15, ChronoUnit.MINUTES));
        userContainFriendRepository.save(userContainFriend3);

        //firstFriendship  --- 4 --without Logged user

        User notHisFriend = new User();
        notHisFriend.setNick("MarkIgorFriends62");
        notHisFriend.setPassword("somePass");
        notHisFriend = userRepository.save(hisFriend3);

        User anotherUSer = new User();
        anotherUSer.setNick("MarkIgorFriends72");
        anotherUSer.setPassword("somePass");
        anotherUSer = userRepository.save(anotherUSer);


        UserContainFriend userContainFriend4 = new UserContainFriend();

        userContainFriend4.setUser1(hisFriend3);
        userContainFriend4.setUser2(notHisFriend);
        userContainFriend4.setDateFriendShip(Instant.now().plus(20, ChronoUnit.MINUTES));
        userContainFriendRepository.save(userContainFriend4);



        String content1 = "stara";
        Instant sentDate1 = Instant.now().plus(1, ChronoUnit.DAYS);

        String content2 = "druga";
        Instant sentDate2 = Instant.now().plus(2, ChronoUnit.DAYS);

        String content3 = "najmlodsza";
        Instant sentDate3 = Instant.now().plus(3, ChronoUnit.DAYS);


        Message message1 = new Message();
        message1.setContent(content1);
        message1.setSentDate(sentDate1);
        message1.setSender(hisFriend1);
        message1.setReceiver(loggedUser);
        message1.setRead(true);
        messageRepository.save(message1);

        Message message2 = new Message();
        message2.setContent(content2);
        message2.setSentDate(sentDate2);
        message2.setSender(hisFriend2);
        message2.setRead(false);
        message2.setReceiver(loggedUser);

        messageRepository.save(message2);

//
        Message message3 = new Message();
        message3.setContent(content3);
        message3.setSentDate(sentDate3);
        message3.setSender(notHisFriend);
        message3.setRead(false);
        message3.setReceiver(hisFriend3);

        messageRepository.save(message3);


        //when

        List<Message> noReadMessages = messageRepository.getNoReadMessages(loggedUser.getIdUser());

        Assert.assertEquals(1, noReadMessages.size());

    }

}