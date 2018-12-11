package com.wildBirds.BlueChat.api.controllers;


import com.wildBirds.BlueChat.api.dto.UserDto;
import com.wildBirds.BlueChat.api.dto.UserDtoPass;
import com.wildBirds.BlueChat.domain.model.UserFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserFacade userFacade;

    public ResponseEntity registerUser(@RequestBody UserDtoPass userDtoPass){
        UserDto registerNewUser = userFacade.registerNewUser(userDtoPass);

        return new ResponseEntity(registerNewUser, HttpStatus.OK);
    }
}
