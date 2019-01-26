package com.wildBirds.BlueChat.domain.model;

import com.wildBirds.BlueChat.api.rest.dto.ContactMessageDto;
import com.wildBirds.BlueChat.api.rest.dto.FriendsDto;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ContactMessageFacadeTest extends ConfigurationTest {


    private Logger logger = LoggerFactory.getLogger(ContactMessageFacadeTest.class);


    @Test
    public void shouldSaveMessage() {
        logger.info("Running test >> shouldSaveMessage");

        //given
        ContactMessageDto contactMessageDto = new ContactMessageDto();
        String email = "contactMessage@wp.pl";
        String message = "Your application is brilliant";

        contactMessageDto.setEmail(email);
        contactMessageDto.setMessage(message);
        contactMessageDto.setSentDate(Instant.now());

        //when
        ContactMessageDto savedMessage = contactMessageFacade.saveMessage(contactMessageDto);
        //then

        Assert.assertNotNull(savedMessage.getIdContactMessage());
        Assert.assertEquals(email, savedMessage.getEmail());
        Assert.assertEquals(message, savedMessage.getMessage());
    }

    @Test
    @Transactional
    public void shouldGetMessage() {
        logger.info("Running test >> shouldGetMessage");

        //given
        ContactMessageDto contactMessageDto = new ContactMessageDto();
        String email = "contactMessage2@wp.pl";
        String message = "Your application is brilliant";

        contactMessageDto.setEmail(email);
        contactMessageDto.setMessage(message);
        contactMessageDto.setSentDate(Instant.now());

        ContactMessageDto savedMessage = contactMessageFacade.saveMessage(contactMessageDto);

        Long idContactMessage = savedMessage.getIdContactMessage();
        //when

        ContactMessageDto fetchMessageById = contactMessageFacade.getMessage(idContactMessage);

        //then
        Assert.assertEquals(fetchMessageById.getMessage(), message);
        Assert.assertEquals(fetchMessageById.getEmail(), email);

    }

    @Test
    @Transactional
    public void getAllOrderedByDateAsc() {
        //given
        logger.info("Running test >> getAllOrderedByDateAsc");

        //given
        ContactMessageDto contactMessageDto = new ContactMessageDto();
        String email1 = "contactMessage1@wp.pl";
        String message1 = "Your application is brilliant";

        contactMessageDto.setEmail(email1);
        contactMessageDto.setMessage(message1);
        contactMessageDto.setSentDate(Instant.now());

        contactMessageFacade.saveMessage(contactMessageDto);

        ContactMessageDto contactMessageDto2 = new ContactMessageDto();
        String email2 = "contactMessage2@wp.pl";
        String message2 = "Your application is brilliant";

        contactMessageDto2.setEmail(email2);
        contactMessageDto2.setMessage(message2);
        contactMessageDto2.setSentDate(Instant.now().plus(5, ChronoUnit.MINUTES));
        contactMessageFacade.saveMessage(contactMessageDto2);

        ContactMessageDto contactMessageDto3 = new ContactMessageDto();
        String email3 = "contactMessage3@wp.pl";
        String message3 = "Your application is brilliant";

        contactMessageDto3.setEmail(email3);
        contactMessageDto3.setMessage(message3);
        contactMessageDto3.setSentDate(Instant.now().plus(15, ChronoUnit.MINUTES));
        contactMessageFacade.saveMessage(contactMessageDto3);

        //when

        List<ContactMessageDto> allOrderedByDateAsc = contactMessageFacade.getAllOrderedByDateAsc();

        //then

        Assert.assertEquals(allOrderedByDateAsc.get(0).getEmail(), email3);
        Assert.assertEquals(allOrderedByDateAsc.get(2).getEmail(), email1);

    }

    @Test
    @Transactional
    public void shouldReturnFriendsDtoContainAmountOfNoReadMessage() {
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

        List<FriendsDto> friendsWithNoReadMessage = userContainFriendFacade.getUserContainFriend(loggedUser.getIdUser());

        System.out.println(friendsWithNoReadMessage);


    }
}