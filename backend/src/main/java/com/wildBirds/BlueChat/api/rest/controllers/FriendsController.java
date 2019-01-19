package com.wildBirds.BlueChat.api.rest.controllers;

import com.wildBirds.BlueChat.api.rest.dto.FriendsDto;
import com.wildBirds.BlueChat.domain.model.UserContainFriendFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
        Long value = Long.valueOf(idUser);
        log.info(value + " <<<<<<<<<<<<<<<<<<<<<<<<<");

        List<FriendsDto> userContainFriend = userContainFriendFacade.getUserContainFriend(value);
        return new ResponseEntity(userContainFriend, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity removeFriendship(@RequestBody FriendsDto friendsDto){
        userContainFriendFacade.remove(friendsDto);

        return new ResponseEntity(HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping
    public ResponseEntity addFriendship(@RequestBody FriendsDto friendsDto,
                                        @RequestParam String idUser){
        FriendsDto response = userContainFriendFacade.addFriendship(Long.valueOf(idUser), friendsDto);


        return new ResponseEntity(response, HttpStatus.OK);
    }
}
