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

    // TODO: 17.12.2018 have to finish impl , add handling exceptions
    @CrossOrigin
    @PostMapping("addChannel")
    public ResponseEntity addChannel(@RequestBody ChannelDto channelDto) {
        ChannelDto response = channelFacade.addChannel(channelDto);


        return new ResponseEntity(response, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("addUser")
    public ResponseEntity addUser(@RequestParam String userId,
                                  @RequestParam String channelId) {

        // TODO: 17.12.2018 Can it by paramater ? 
        System.out.println(userId);
        System.out.println(channelId);
        Long useId = Long.valueOf(userId);
        Long chanId = Long.valueOf(channelId);

        ChannelDto response = channelFacade.addUserToChannel(useId, chanId);

        return new ResponseEntity(response, HttpStatus.OK);
    }
    @CrossOrigin
    @DeleteMapping({"deleteChannel"})
    public ResponseEntity deleteChannel(@RequestBody ChannelDto channelDto) {

        channelFacade.removeChannel(channelDto);

        return new ResponseEntity(HttpStatus.OK);
    }

}
