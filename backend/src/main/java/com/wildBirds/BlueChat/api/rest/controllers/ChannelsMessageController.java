package com.wildBirds.BlueChat.api.rest.controllers;

import com.wildBirds.BlueChat.api.rest.dto.ChannelsMessageDto;
import com.wildBirds.BlueChat.domain.model.ChannelsMessageFacade;
import com.wildBirds.BlueChat.domain.model.exceptions.MessageServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/channelMessages")
public class ChannelsMessageController {

    private ChannelsMessageFacade channelsMessageFacade;
    private Logger log = LoggerFactory.getLogger(ChannelsMessageController.class);

    public ChannelsMessageController(ChannelsMessageFacade channelsMessageFacade) {
        this.channelsMessageFacade = channelsMessageFacade;
    }

    @CrossOrigin
    @GetMapping
    @RequestMapping
    public ResponseEntity getConversation(@RequestParam("channelId") String channelId,
                                          @RequestParam("limit") String limit,
                                          @RequestParam("toBound") String toBound) {

        try {
            Long idChannelLong = Long.valueOf(channelId);
            Integer limitInt = Integer.valueOf(limit);
            Integer toBoundInt = Integer.valueOf(toBound);

            List<ChannelsMessageDto> conversation = channelsMessageFacade.getConversation(idChannelLong, limitInt, toBoundInt);
            log.info("Method getConversation ", conversation.toString());
            return new ResponseEntity(conversation, HttpStatus.OK);

        } catch (NumberFormatException e) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", e.getMessage());
            log.error("Method getConversation", e.getMessage());
            return new ResponseEntity(headers, HttpStatus.BAD_REQUEST);

        } catch (MessageServiceException e) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", e.getMessage());
            log.error("Method getConversation", e.getMessage());
            return new ResponseEntity(headers, HttpStatus.BAD_REQUEST);
        }
    }
    @CrossOrigin
    @PostMapping
    public ResponseEntity sendMessage(@RequestBody ChannelsMessageDto channelsMessageDto) {

        try {
            channelsMessageDto.setSentDate(Instant.now());
            ChannelsMessageDto savedMessages = channelsMessageFacade.saveMessage(channelsMessageDto);
            log.info("Method sendMessage ", savedMessages.toString());
            return new ResponseEntity(savedMessages, HttpStatus.OK);
        } catch (MessageServiceException e) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", e.getMessage());
            log.error("Method sendMessage", e.getMessage());
            return new ResponseEntity(headers, HttpStatus.BAD_REQUEST);
        } catch (DataIntegrityViolationException e) {
            e.fillInStackTrace();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", e.getMostSpecificCause().getMessage());
            log.error("Method sendMessage" + e.getMostSpecificCause().getMessage());
            return new ResponseEntity(headers, HttpStatus.CONFLICT);
        }
    }


}
