package com.wildBirds.BlueChat.api.rest.controllers;


import com.wildBirds.BlueChat.api.rest.dto.UserDto;
import com.wildBirds.BlueChat.api.rest.dto.UserDtoPass;
import com.wildBirds.BlueChat.domain.model.UserFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserFacade userFacade;
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
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
    @GetMapping("{idUser}")
    public ResponseEntity getById(@PathVariable Long idUser) {
        return new ResponseEntity(userFacade.getById(idUser), HttpStatus.OK);
    }
}
