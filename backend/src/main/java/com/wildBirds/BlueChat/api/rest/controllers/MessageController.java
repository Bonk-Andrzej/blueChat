package com.wildBirds.BlueChat.api.rest.controllers;

import com.wildBirds.BlueChat.api.rest.dto.MessageDto;
import com.wildBirds.BlueChat.domain.model.MessageFacade;
import com.wildBirds.BlueChat.domain.model.exceptions.MessageServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private MessageFacade messageFacade;
    private Logger log = LoggerFactory.getLogger(MessageController.class);


    public MessageController(MessageFacade messageFacade) {
        this.messageFacade = messageFacade;
    }

    @CrossOrigin
    @GetMapping("/conversation")
    public ResponseEntity getConversation(@RequestParam String idSender,
                                          @RequestParam String idReceiver,
                                          @RequestParam String limit,
                                          @RequestParam String toBound) {

        try {
            Long idSenderInt = Long.valueOf(idSender);
            Long idReceiverrInt = Long.valueOf(idReceiver);
            Integer limitInt = Integer.valueOf(limit);
            Integer toBoundInt = Integer.valueOf(toBound);

            List<MessageDto> conversation = messageFacade.getConversation(idSenderInt, idReceiverrInt, limitInt, toBoundInt);
            log.info("Method getConversation", conversation.toString());
            return new ResponseEntity(conversation, HttpStatus.OK);

        } catch (NumberFormatException e) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", e.getMessage());
            log.error("Method getConversation", e.getMessage());
            return new ResponseEntity(headers, HttpStatus.BAD_REQUEST);
        } catch (MessageServiceException e) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", e.getMessage());
            log.error("Method sendMessage", e.getMessage());
            return new ResponseEntity(headers, HttpStatus.BAD_REQUEST);
        }

    }
    @CrossOrigin
    @PostMapping()
    public ResponseEntity sendMessages(@RequestBody MessageDto messageDto){

        try {
            messageDto.setSentDate(Instant.now());
            MessageDto response = messageFacade.saveMessage(messageDto);
            log.info("Method sendMessage", response.toString());
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (MessageServiceException e) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", e.getMessage());
            log.error("Method sendMessage", e.getMessage());
            return new ResponseEntity(headers, HttpStatus.BAD_REQUEST);
        }
    }
}
