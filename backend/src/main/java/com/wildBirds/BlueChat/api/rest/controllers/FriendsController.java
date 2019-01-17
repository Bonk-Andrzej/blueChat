package com.wildBirds.BlueChat.api.rest.controllers;

import com.wildBirds.BlueChat.api.rest.dto.FriendsDto;
import com.wildBirds.BlueChat.domain.model.UserContainFriendFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class FriendsController {

    private UserContainFriendFacade userContainFriendFacade;
    private Logger log = LoggerFactory.getLogger(UserContainFriendFacade.class);

    public ResponseEntity getFriendshipList(@RequestParam String idUser) {

        List<FriendsDto> userContainFriend = userContainFriendFacade.getUserContainFriend(Long.valueOf(idUser));
        return new ResponseEntity(userContainFriend, HttpStatus.OK);
    }

}
