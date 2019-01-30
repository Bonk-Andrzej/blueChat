package com.wildBirds.BlueChat.api.rest.controllers;

import com.wildBirds.BlueChat.api.rest.dto.ContactMessageDto;
import com.wildBirds.BlueChat.domain.model.ContactMessageFacade;
import com.wildBirds.BlueChat.domain.model.EmailFacade;
import com.wildBirds.BlueChat.domain.model.exceptions.ContactMessageException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/contact")
public class ContactMessageController {


    private ContactMessageFacade contactMessageFacade;
    private Logger log = LoggerFactory.getLogger(ChannelsMessageController.class);

    public ContactMessageController(ContactMessageFacade contactMessageFacade, EmailFacade emailFacade) {
        this.contactMessageFacade = contactMessageFacade;
    }

    // TODO: 16.01.2019 SHOULD TESTING !
    @CrossOrigin
    @PostMapping
    public ResponseEntity sendMessage(@RequestBody ContactMessageDto contactMessageDto) {

        try {
            contactMessageDto.setSentDate(Instant.now());
            ContactMessageDto savedMessages = contactMessageFacade.saveMessage(contactMessageDto);

            log.info("Method sendMessage ", savedMessages.toString());
            return new ResponseEntity(HttpStatus.OK);
        } catch (ContactMessageException e) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", e.getMessage());
            log.error("Method sendMessage", e.getMessage());
            return new ResponseEntity(headers, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", e.getMessage());
            log.error("Method sendMessage", e.getMessage());
            return new ResponseEntity(headers, HttpStatus.BAD_REQUEST);
        }
    }
}
