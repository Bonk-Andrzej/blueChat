package com.wildBirds.BlueChat.api.rest.controllers;

import com.wildBirds.BlueChat.api.rest.dto.MessageDto;
import com.wildBirds.BlueChat.domain.model.MessageFacade;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/messages")
@AllArgsConstructor
public class MessageController {

    private MessageFacade messageFacade;

    @CrossOrigin
    @GetMapping("/conversation")
    public ResponseEntity getConversation(@RequestParam String idSender,
                                          @RequestParam String idReceiver,
                                          @RequestParam String limit,
                                          @RequestParam String toBound) {


        System.out.println(idSender);

        System.out.println(idReceiver);

        System.out.println(limit);

        System.out.println(toBound);

//        List<MessageDto> conversation = messageFacade.getConversation(idSender, idReceiver, limit, toBound);

//        return new ResponseEntity("OK", HttpStatus.OK);
return null;
    }
    @CrossOrigin
    @PostMapping()
    public ResponseEntity sendMessages(@RequestBody MessageDto messageDto){

        messageDto.setSentDate(Instant.now());
        MessageDto response = messageFacade.saveMessage(messageDto);

        return new ResponseEntity(response, HttpStatus.OK);
    }
}
