package com.wildBirds.BlueChat.api.rest.controllers;

import com.wildBirds.BlueChat.api.rest.dto.ChannelDto;
import com.wildBirds.BlueChat.domain.model.ChannelFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/channel")
public class ChannelController {

    private Logger log = LoggerFactory.getLogger(ChannelController.class);
    private ChannelFacade channelFacade;

    public ChannelController(ChannelFacade channelFacade) {
        this.channelFacade = channelFacade;
    }

    @CrossOrigin
    @PostMapping("addChannel")
    public ResponseEntity addChannel(@RequestBody ChannelDto channelDto) {
        ChannelDto response = channelFacade.addChannel(channelDto);


        return new ResponseEntity(response, HttpStatus.OK);
    }

//    @CrossOrigin
//    @PostMapping("addUser")
//    public ResponseEntity addUser(@RequestBody ChannelDto channelDto) {
//        ChannelDto response = channelFacade.addUserToChannel(channelDto);
//
//        return new ResponseEntity(response, HttpStatus.OK);
//    }
    @CrossOrigin
    @DeleteMapping({"deleteChannel"})
    public ResponseEntity deleteChannel(@RequestBody ChannelDto channelDto) {

        System.out.println("DELETE");
        System.out.println(channelDto.getIdChannel());
        channelFacade.removeChannel(channelDto);

        return new ResponseEntity(HttpStatus.OK);
    }

}
