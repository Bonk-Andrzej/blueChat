package com.wildBirds.BlueChat.api.controllers;

import com.wildBirds.BlueChat.api.dto.ChannelsMessageDto;
import com.wildBirds.BlueChat.domain.model.ChannelsMessageFacade;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/chan/messages")
public class ChannelsMessageController {

    private ChannelsMessageFacade channelsMessageFacade;

    @CrossOrigin
    @GetMapping
    @RequestMapping
    public ResponseEntity getConversation(@RequestParam("idChannel") Integer idChannel,
                                          @RequestParam("limit") Integer limit,
                                          @RequestParam("toBound") Integer toBound) {

        List<ChannelsMessageDto> conversation = channelsMessageFacade.getConversation(idChannel, limit, toBound);

        return new ResponseEntity(conversation, HttpStatus.OK);
    }
    @CrossOrigin
    @PostMapping
    public ResponseEntity addMessage(@RequestBody ChannelsMessageDto channelsMessageDto) {

        ChannelsMessageDto savedMessages = channelsMessageFacade.addMessages(channelsMessageDto);

        return new ResponseEntity(savedMessages, HttpStatus.OK);
    }


}
