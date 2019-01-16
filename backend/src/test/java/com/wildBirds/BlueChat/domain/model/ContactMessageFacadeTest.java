package com.wildBirds.BlueChat.domain.model;

import com.wildBirds.BlueChat.api.rest.dto.ContactMessageDto;
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
}