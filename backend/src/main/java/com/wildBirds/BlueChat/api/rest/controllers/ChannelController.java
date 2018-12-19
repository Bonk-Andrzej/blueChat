package com.wildBirds.BlueChat.api.rest.controllers;

import com.wildBirds.BlueChat.api.rest.dto.ChannelDto;
import com.wildBirds.BlueChat.domain.model.ChannelFacade;
import com.wildBirds.BlueChat.domain.model.exceptions.ChannelServiceExceptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping
    public ResponseEntity getChannels(){
        List<ChannelDto> channels = channelFacade.getChannels();

        return new ResponseEntity(channels, HttpStatus.OK);

    }

    @CrossOrigin
    @PostMapping("addChannel")
    public ResponseEntity addChannel(@RequestBody ChannelDto channelDto) {

        try {
            ChannelDto response = channelFacade.addChannel(channelDto);
            log.info("Method addChannel", response.toString());
            return new ResponseEntity(response, HttpStatus.OK);

        } catch (ChannelServiceExceptions e) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", e.getMessage());
            log.error("Method addChannel ", e.getMessage());
            return new ResponseEntity(headers, HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin
    @PostMapping("addUser")
    public ResponseEntity addUser(@RequestParam String userId,
                                  @RequestParam String channelId) {

        try {
            Long useId = Long.valueOf(userId);
            Long chanId = Long.valueOf(channelId);

            ChannelDto response = channelFacade.addUserToChannel(useId, chanId);
            log.info("Method addUser ", response.toString());
            return new ResponseEntity(response, HttpStatus.OK);

        } catch (NumberFormatException e) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", e.getMessage());
            log.error("Method addUser", e.getMessage());
            return new ResponseEntity(headers, HttpStatus.BAD_REQUEST);
        } catch (ChannelServiceExceptions e) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", e.getMessage());
            log.error("Method addUser ", e.getMessage());
            return new ResponseEntity(headers, HttpStatus.BAD_REQUEST);
        }
    }
    @CrossOrigin
    @DeleteMapping({"deleteChannel"})
    public ResponseEntity deleteChannel(@RequestBody ChannelDto channelDto) {

        try {
            channelFacade.removeChannel(channelDto);
            log.info("Method deleteChannel ", channelDto.toString());
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", e.getMessage());
            log.error("Method deleteChannel ", e.getMessage());
            return new ResponseEntity(headers, HttpStatus.BAD_REQUEST);
        }
    }
}
