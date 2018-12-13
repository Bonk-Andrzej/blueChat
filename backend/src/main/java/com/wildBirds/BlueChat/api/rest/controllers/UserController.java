package com.wildBirds.BlueChat.api.rest.controllers;


import com.wildBirds.BlueChat.api.rest.dto.UserDto;
import com.wildBirds.BlueChat.api.rest.dto.UserDtoPass;
import com.wildBirds.BlueChat.domain.model.UserFacade;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class UserController {


    private UserFacade userFacade;

    @PostMapping
    @CrossOrigin
    public ResponseEntity registerUser(@RequestBody UserDtoPass userDtoPass){
        UserDto registerNewUser = userFacade.registerNewUser(userDtoPass);

        return new ResponseEntity(registerNewUser, HttpStatus.OK);
    }
}
