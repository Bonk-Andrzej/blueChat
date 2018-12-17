package com.wildBirds.BlueChat.api.rest.controllers;

import com.wildBirds.BlueChat.api.rest.dto.UserDto;
import com.wildBirds.BlueChat.api.rest.dto.UserDtoPass;
import com.wildBirds.BlueChat.domain.model.UserFacade;
import com.wildBirds.BlueChat.domain.model.exceptions.UserNotExistExceptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rpc")

public class RpsController {
    private UserFacade userFacade;
    private Logger log = LoggerFactory.getLogger(RpsController.class);
    public RpsController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserDtoPass userDtoPass){
        try {
            UserDto loginUser = userFacade.loginUser(userDtoPass);

            return new ResponseEntity(loginUser, HttpStatus.OK);
        } catch (UserNotExistExceptions e) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", e.getMessage());
            log.error("Method login", e.getMessage());
            return new ResponseEntity(headers, HttpStatus.BAD_REQUEST);
        }
    }

}
