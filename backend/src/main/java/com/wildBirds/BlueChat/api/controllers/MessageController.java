package com.wildBirds.BlueChat.api.controllers;

import com.wildBirds.BlueChat.api.dto.MessageDto;
import com.wildBirds.BlueChat.domain.model.MessageFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private MessageFacade messageFacade;

    @CrossOrigin
    @GetMapping("/by")
    public ResponseEntity getConversation(@RequestParam Integer idSender,
                                          @RequestParam Integer idReceiver,
                                          @RequestParam Integer limit,
                                          @RequestParam Integer toBound) {

        List<MessageDto> conversation = messageFacade.getConversation(idSender, idReceiver, limit, toBound);

        return new ResponseEntity(conversation, HttpStatus.OK);
    }
    @CrossOrigin
    @PostMapping()
    public ResponseEntity sendMessages(@RequestBody MessageDto messageDto){
        MessageDto response = messageFacade.sendMessage(messageDto);

        return new ResponseEntity(response, HttpStatus.OK);
    }
}
