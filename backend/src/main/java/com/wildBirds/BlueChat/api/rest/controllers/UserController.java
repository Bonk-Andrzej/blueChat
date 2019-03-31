package com.wildBirds.BlueChat.api.rest.controllers;


import com.wildBirds.BlueChat.api.rest.dto.UserDto;
import com.wildBirds.BlueChat.api.rest.dto.UserDtoPass;
import com.wildBirds.BlueChat.api.rest.dto.UserDtoShort;
import com.wildBirds.BlueChat.api.rest.dto.UserDtoWithLastMessage;
import com.wildBirds.BlueChat.domain.model.UserFacade;
import com.wildBirds.BlueChat.domain.model.UserWithLasMessageFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserFacade userFacade;
    private UserWithLasMessageFacade userWithLasMessageFacade;
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserFacade userFacade, UserWithLasMessageFacade userWithLasMessageFacade) {
        this.userFacade = userFacade;
        this.userWithLasMessageFacade = userWithLasMessageFacade;
    }

    @PostMapping
    @CrossOrigin
    public ResponseEntity registerUser(@RequestBody UserDtoPass userDtoPass){
        try {
            UserDto registerNewUser = userFacade.registerNewUser(userDtoPass);
            logger.info("REGISTERED USER >> ", userDtoPass.toString());
            return new ResponseEntity(registerNewUser, HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            HttpHeaders headers = new HttpHeaders();
            logger.info("INVALID REGISTERED USER User already excise >> " , userDtoPass.toString());
            headers.add("Error","User already excise");
            return new ResponseEntity(headers,HttpStatus.CONFLICT);
        } catch (Exception e) {
            HttpHeaders headers = new HttpHeaders();
            logger.debug("INVALID REGISTER", e.fillInStackTrace());
            headers.add("Error","Bad request");
            return new ResponseEntity(headers,HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin
    @GetMapping("{idUser}")
    public ResponseEntity getById(@PathVariable Long idUser) {
        try {
            UserDto response = userFacade.getById(idUser);
            logger.info("REGISTERED USER >> ", response.toString());
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            HttpHeaders headers = new HttpHeaders();
            logger.debug("INVALID REGISTER", e.fillInStackTrace());
            headers.add("Error", "Bad request");
            return new ResponseEntity(headers, HttpStatus.BAD_REQUEST);
        }
    }
//    @GetMapping
//    @CrossOrigin
//    public ResponseEntity getAll(){
//        try {
//            List<UserDto> userDtoList = userFacade.getUsers();
//
//            userDtoList.stream().forEach(userDto -> System.out.println(userDto));
//            return new ResponseEntity(userDtoList, HttpStatus.OK);
//        } catch (Exception e) {
//
//            logger.debug("INVALID GETALL", e.fillInStackTrace());
//            HttpHeaders headers = new HttpHeaders();
//            headers.add("Error","Bad request");
//            return new ResponseEntity(headers,HttpStatus.BAD_REQUEST);
//        }

//    }

    @CrossOrigin
    @PatchMapping
    public ResponseEntity updateUser(@RequestBody UserDto userDto) {
        try {
//            logger.info("UPDATE USER BEFORE " + userDto.toString());
            UserDto response = userFacade.updateUser(userDto);
//            logger.info("UPDATE USER AFTER UPDATE " + response.toString());
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            HttpHeaders headers = new HttpHeaders();
            logger.debug("ERROR EDIT", e.fillInStackTrace());
            headers.add("Error", "Bad request");
            return new ResponseEntity(headers, HttpStatus.BAD_REQUEST);
        }

    }

    @CrossOrigin
    @GetMapping("/short/{phrase}")
    public ResponseEntity getShortListByPhrase(@PathVariable String phrase) {

        try {
            List<UserDtoShort> response = userFacade.nickContainPhrase(phrase);
            logger.info("GET USER LIST BY PHRASE  >> ", response.toString());
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            HttpHeaders headers = new HttpHeaders();
            logger.debug("ERROR GET USER LIST BY PHRASE  >>", e.fillInStackTrace());
            headers.add("Error", "Bad request");
            return new ResponseEntity(headers, HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin
    @GetMapping("/noRead/{idUser}")
    public ResponseEntity getFirstNoReadMessages(@PathVariable String idUser){


        List<UserDtoWithLastMessage> response = userWithLasMessageFacade.getFirstNoReadMessages(Long.valueOf(idUser));

        return new ResponseEntity(response, HttpStatus.OK);
    }
}
