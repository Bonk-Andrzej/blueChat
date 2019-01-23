package com.wildBirds.BlueChat.api.rest.controllers;

import com.wildBirds.BlueChat.api.rest.dto.FriendsDto;
import com.wildBirds.BlueChat.domain.model.UserContainFriendFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/friends")
public class FriendsController {

    private UserContainFriendFacade userContainFriendFacade;
    private Logger log = LoggerFactory.getLogger(FriendsController.class);

    public FriendsController(UserContainFriendFacade userContainFriendFacade ) {
        this.userContainFriendFacade = userContainFriendFacade;
    }

    // TODO: 17.01.2019 SHOULD TESTING
    // TODO: 17.01.2019 SHOULD HANDLE EXCEPTIONS

    @CrossOrigin
    @GetMapping
    public ResponseEntity getFriendshipList(@RequestParam String idUser) {
        try {
            Long value = Long.valueOf(idUser);
//            List<FriendsDto> response = userContainFriendFacade.getUserContainFriend(value);
            List<FriendsDto> response = userContainFriendFacade.getFriendsWithNoReadMessage(value);
            log.info("Method getFriendshipList ", response.toString());
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", e.getMessage());
            log.error("Method getFriendshipList ", e.getMessage());
            return new ResponseEntity(headers, HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity removeFriendship(@RequestBody FriendsDto friendsDto){
        try {
            userContainFriendFacade.remove(friendsDto);
            log.info("Method removeFriendship ", friendsDto.toString());
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", e.getMessage());
            log.error("Method removeFriendship ", e.getMessage());
            return new ResponseEntity(headers, HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin
    @DeleteMapping
    public ResponseEntity addFriendship(@RequestBody FriendsDto friendsDto,
                                        @RequestParam String idUser){

        try {
            FriendsDto response = userContainFriendFacade.addFriendship(Long.valueOf(idUser), friendsDto);
            log.info("Method addFriendship ", response.toString());

            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", e.getMessage());
            log.error("Method addFriendship ", e.getMessage());
            return new ResponseEntity(headers, HttpStatus.BAD_REQUEST);
        }
    }
}
