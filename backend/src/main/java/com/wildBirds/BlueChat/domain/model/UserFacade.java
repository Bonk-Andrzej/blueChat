package com.wildBirds.BlueChat.domain.model;


import com.wildBirds.BlueChat.api.rest.dto.UserDto;
import com.wildBirds.BlueChat.api.rest.dto.UserDtoPass;
import com.wildBirds.BlueChat.api.webSocket.controllers.MessageControllerWSR;
import com.wildBirds.BlueChat.domain.model.exceptions.UserNotExistExceptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;


public class UserFacade {

    private UserRepository userRep;
    private UserService userService;
    private MessageControllerWSR wsr;
    private Logger log = LoggerFactory.getLogger(UserFacade.class);

    public UserFacade(UserRepository userRep, UserService userService, MessageControllerWSR wsr) {
        this.userRep = userRep;
        this.userService = userService;
        this.wsr = wsr;
    }


//    public List<UserDto> getUsers() {
//        List<User> getUsers = userRep.findAll();
//        List<Long> authorizedSessionsIdentifications = wsr.getAuthorizedSessionsIdentifications();
//        List<UserDto> userDtoList = getUsers.stream()
//                .map(user -> userService.toDto(user))
//                .map(userDto -> {
//                    if (authorizedSessionsIdentifications.contains(userDto.getIdUser())) {
////                        userDto.setActive(true);
//                    }
//                    return userDto;
//                })
//                .collect(Collectors.toList());
//        log.info("Method getUsers ", userDtoList.toString());
//        return userDtoList;
//    }

    public UserDto registerNewUser(UserDtoPass userDtoPass){
        User mappedUser = userService.toEntity(userDtoPass);
        User registeredUser = userRep.save(mappedUser);

        UserDto response = userService.toDto(registeredUser);
        return response;
    }

    public UserDto loginUser(UserDtoPass userDtoPass){

        User onlineUser = userRep.getUserByNickAndPassword(userDtoPass.getNick(), userDtoPass.getPassword());
        if (onlineUser == null) {
            log.error("Method loginUser ", "User not exist or invalid login or password");
           throw new  UserNotExistExceptions("User not exist or invalid login or password");
        }

        UserDto responseDto = userService.toDto(onlineUser);
        log.info("Method loginUser ", responseDto.toString());
        return responseDto;
    }

    @Transactional
    public UserDto getById(Long idUser) {
        User one = userRep.getOne(idUser);

        UserDto userDto = userService.toDto(one);

        return userDto;
    }
}
