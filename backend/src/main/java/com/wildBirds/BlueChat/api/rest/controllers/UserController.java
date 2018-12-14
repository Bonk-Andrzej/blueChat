package com.wildBirds.BlueChat.api.rest.controllers;


import com.wildBirds.BlueChat.api.rest.dto.UserDto;
import com.wildBirds.BlueChat.api.rest.dto.UserDtoPass;
import com.wildBirds.BlueChat.domain.model.UserFacade;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {


    private UserFacade userFacade;

    @PostMapping
    @CrossOrigin
    public ResponseEntity registerUser(@RequestBody UserDtoPass userDtoPass){
        try {

            UserDto registerNewUser = userFacade.registerNewUser(userDtoPass);

            return new ResponseEntity(registerNewUser, HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error","User already excise");
            return new ResponseEntity(headers,HttpStatus.CONFLICT);
        } catch (Exception e) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error","Bad request");
            return new ResponseEntity(headers,HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping
    @CrossOrigin
    public ResponseEntity getAll(){
        try {
            List<UserDto> userDtoList = userFacade.getUsers();

            return new ResponseEntity(userDtoList, HttpStatus.OK);
        } catch (Exception e) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error","Bad request");
            return new ResponseEntity(headers,HttpStatus.BAD_REQUEST);
        }
    }
}
