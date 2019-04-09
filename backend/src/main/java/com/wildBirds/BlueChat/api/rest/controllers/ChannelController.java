package com.wildBirds.BlueChat.api.rest.controllers;

import com.wildBirds.BlueChat.api.rest.dto.ChannelDto;
import com.wildBirds.BlueChat.api.rest.dto.ChannelDtoCreate;
import com.wildBirds.BlueChat.api.rest.dto.ChannelDtoShort;
import com.wildBirds.BlueChat.domain.model.ChannelFacade;
import com.wildBirds.BlueChat.domain.model.exceptions.ChannelServiceExceptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
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

    @CrossOrigin
    @GetMapping("shorts")
    public ResponseEntity getShortList() {
        try {
            List<ChannelDtoShort> response = channelFacade.getChannelsShort();
            log.info("Method getShortList", response.toString());
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (ChannelServiceExceptions e) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", e.getMessage());
            log.error("Method getShortList ", e.getMessage());
            return new ResponseEntity(headers, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", e.getMessage());
            log.error("Method getShortList ", e.getMessage());
            return new ResponseEntity(headers, HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin
    @GetMapping("shorts/phrase/{phrase}")
    public ResponseEntity getListByPhrase(@PathVariable String phrase){

        List<ChannelDtoShort> channelsByPhrase = channelFacade.getChannelsByPhrase(phrase);

        return new ResponseEntity(channelsByPhrase, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/shorts/{idUser}")
    public ResponseEntity getShortList(@PathVariable String idUser){
        try {
        List<ChannelDtoShort> response = channelFacade.getChannelsShort(Long.valueOf(idUser));
            log.info("Method getShortList by user " + idUser, response.toString());
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (ChannelServiceExceptions e) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", e.getMessage());
            log.error("Method getShortList by user " + idUser, e.getMessage());
            return new ResponseEntity(headers, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", e.getMessage());
            log.error("Method getShortList by user " + idUser, e.getMessage());
            return new ResponseEntity(headers, HttpStatus.BAD_REQUEST);
        }

    }

    @CrossOrigin
    @GetMapping({"{idChannel}"})
    public ResponseEntity getById(@PathVariable String idChannel) {
        try {
            ChannelDto channelDto = channelFacade.getById(Long.valueOf(idChannel));
            log.info("Method getById", channelDto.toString());
            return new ResponseEntity(channelDto, HttpStatus.OK);
        } catch (ChannelServiceExceptions e) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", e.getMessage());
            log.error("Method getById ", e.getMessage());
            return new ResponseEntity(headers, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", e.getMessage());
            log.error("Method getById ", e.getMessage());
            return new ResponseEntity(headers, HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin
    @PostMapping("addChannel")
    public ResponseEntity addChannel(@RequestBody ChannelDtoCreate channelDtoCreate) {

        try {
            ChannelDto response = channelFacade.addChannel(channelDtoCreate);
            log.info("Method addChannel", response.toString());
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (ChannelServiceExceptions e) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", e.getMessage());
            log.error("Method addChannel ", e.getMessage());
            return new ResponseEntity(headers, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
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

        } catch (DataIntegrityViolationException e) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", e.getMessage());
            log.error("Method deleteChannel ", e.getMessage());
            return new ResponseEntity(headers, HttpStatus.BAD_REQUEST);

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

        } catch (Exception e) {
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
        try {
            Long useId = Long.valueOf(idUser);
            Long chanId = Long.valueOf(idChannel);
            ChannelDto response = channelFacade.removeUserFromChannel(useId, chanId);
            log.info("Method removeUser ", response.toString());
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (ChannelServiceExceptions e) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", e.getMessage());
            log.error("Method removeUser ", e.getMessage());
            return new ResponseEntity(headers, HttpStatus.BAD_REQUEST);

        } catch (DataIntegrityViolationException e) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", e.getMessage());
            log.error("Method removeUser ", e.getMessage());
            return new ResponseEntity(headers, HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", e.getMessage());
            log.error("Method removeUser ", e.getMessage());
            return new ResponseEntity(headers, HttpStatus.BAD_REQUEST);
        }
    }
}
