package com.wildBirds.BlueChat.api.rest.controllers;

import com.wildBirds.BlueChat.api.rest.dto.ChannelDto;
import com.wildBirds.BlueChat.api.rest.dto.ChannelDtoShort;
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

    // TODO: 17.12.2018 have to finish impl , add handling exceptions and logger

    // TODO: 16.01.2019 HAVE TO TESTING

    // TODO: 16.01.2019 add handling exception

    /**
     * } catch (DataIntegrityViolationException e) {
     * e.printStackTrace();
     * throw new DataIntegrityViolationException(e.getMessage());
     * }
     */

    @CrossOrigin
    @GetMapping("shorts")
    public ResponseEntity getShortList() {

        List<ChannelDtoShort> channelsShort = channelFacade.getChannelsShort();

        return new ResponseEntity(channelsShort, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping
    public ResponseEntity getChannels() {
        List<ChannelDto> channels = channelFacade.getChannels();

        return new ResponseEntity(channels, HttpStatus.OK);

    }

    @CrossOrigin
    @GetMapping({"idChannel"})
    public ResponseEntity getById(Long idChannel) {
        ChannelDto channelDto = channelFacade.getById(idChannel);

        return new ResponseEntity(channelDto, HttpStatus.OK);
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

    @CrossOrigin
    @PatchMapping("addUser")
    public ResponseEntity addUser(@RequestParam String idUser,
                                  @RequestParam String idChannel) {

        try {
            Long useId = Long.valueOf(idUser);
            Long chanId = Long.valueOf(idChannel);

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
    @PatchMapping("removeUser")
    public ResponseEntity removeUserFromChannel(@RequestParam String idUser,
                                                @RequestParam String idChannel) {
        Long useId = Long.valueOf(idUser);
        Long chanId = Long.valueOf(idChannel);

        ChannelDto response = channelFacade.removeUserFromChannel(useId, chanId);

        return new ResponseEntity(response,HttpStatus.OK);
    }

//    @ExceptionHandler(ChannelServiceExceptions.class)
//    public ResponseEntity channelException(ChannelServiceExceptions e){
//
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity channelException(){
//
//    }


}
