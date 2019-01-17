package com.wildBirds.BlueChat.api.rest.controllers;

import com.wildBirds.BlueChat.api.rest.dto.FriendsDto;
import com.wildBirds.BlueChat.domain.model.UserContainFriendFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/friends")
public class FriendsController {

    private UserContainFriendFacade userContainFriendFacade;
    private Logger log = LoggerFactory.getLogger(FriendsController.class);

    // TODO: 17.01.2019 SHOULD TESTING
    // TODO: 17.01.2019 SHOULD HANDLE EXCEPTIONS

    public ResponseEntity getFriendshipList(@RequestParam String idUser) {

        List<FriendsDto> userContainFriend = userContainFriendFacade.getUserContainFriend(Long.valueOf(idUser));
        return new ResponseEntity(userContainFriend, HttpStatus.OK);
    }

    public ResponseEntity removeFriendship(@RequestBody FriendsDto friendsDto){
        userContainFriendFacade.remove(friendsDto);

        return new ResponseEntity(HttpStatus.OK);
    }

    public ResponseEntity addFriendship(@RequestBody FriendsDto friendsDto,
                                        @RequestParam String idUser){
        FriendsDto response = userContainFriendFacade.addFriendship(Long.valueOf(idUser), friendsDto);


        return new ResponseEntity(response, HttpStatus.OK);
    }
}
