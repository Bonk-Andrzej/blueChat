package com.wildBirds.BlueChat.domain.model;


import com.wildBirds.BlueChat.api.rest.dto.UserDto;
import com.wildBirds.BlueChat.api.rest.dto.UserDtoPass;
import com.wildBirds.BlueChat.api.rest.dto.UserDtoShort;
import com.wildBirds.BlueChat.api.webSocket.controllers.MessageControllerWSR;
import com.wildBirds.BlueChat.domain.model.exceptions.UserNotExistExceptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


public class UserFacade {

    private UserRepository userRep;
    private UserService userService;
    private ChannelFacade channelFacade;
    private PhotoFacade photoFacade;
    private MessageControllerWSR wsr;
    private Logger log = LoggerFactory.getLogger(UserFacade.class);

    public UserFacade(UserRepository userRep,
                      UserService userService,
                      ChannelFacade channelFacade,
                      PhotoFacade photoFacade,
                      MessageControllerWSR wsr) {
        this.userRep = userRep;
        this.userService = userService;
        this.channelFacade = channelFacade;
        this.photoFacade = photoFacade;
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



    public UserDto registerNewUser(UserDtoPass userDtoPass) {
        User mappedUser = userService.toEntity(userDtoPass);

        User registeredUser = userRep.save(mappedUser);

        addBasicSettings(registeredUser);

        UserDto response = userService.toDto(registeredUser);
        return response;
    }

    public UserDto loginUser(UserDtoPass userDtoPass) {

        User onlineUser = userRep.getUserByNickAndPassword(userDtoPass.getNick(), userDtoPass.getPassword());
        if (onlineUser == null) {
            log.error("Method loginUser ", "User not exist or invalid login or password");
            throw new UserNotExistExceptions("User not exist or invalid login or password");
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

    public List<UserDtoShort> nickContainPhrase(String phrase) {

        List<User> users = userRep.nickContainPhrase(phrase);

        List<UserDtoShort> userDtoShortList = users.stream()
                .map(user -> userService.toDtoShort(user))
                .collect(Collectors.toList());
        return userDtoShortList;

    }

    private User addBasicSettings(User registeredUser) {

        Long generalChannel = 1L;
        channelFacade.addUserToChannel(registeredUser.getIdUser(), generalChannel);

        registeredUser.setDescription("I am really enjoy to join in new communicator ;)");
        registeredUser.setProfilePhoto(photoFacade.generatePhoto());
        registeredUser = userRep.save(registeredUser);
        return registeredUser;
    }
}
