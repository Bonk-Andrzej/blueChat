package com.wildBirds.BlueChat.api.controllers;

import com.wildBirds.BlueChat.api.dto.ChannelDto;
import com.wildBirds.BlueChat.domain.model.ChannelFacade;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/channel")
@AllArgsConstructor
public class ChannelController {

    private ChannelFacade channelFacade;

    @CrossOrigin
    @PostMapping("addChannel")
    public ResponseEntity addChannel(@RequestBody ChannelDto channelDto) {
        ChannelDto response = channelFacade.addChannel(channelDto);

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("addUser")
    public ResponseEntity addUser(@RequestBody ChannelDto channelDto) {
        ChannelDto response = channelFacade.addUserToChannel(channelDto);

        return new ResponseEntity(response, HttpStatus.OK);
    }
    @CrossOrigin
    @DeleteMapping({"idChannel"})
    public ResponseEntity deleteChannel(@PathVariable Integer idChannel) {
        channelFacade.removeChannel(idChannel);

        return new ResponseEntity(HttpStatus.OK);
    }

}
