package com.wildBirds.BlueChat.domain.model;


import com.wildBirds.BlueChat.api.rest.dto.UserDto;
import com.wildBirds.BlueChat.api.rest.dto.UserDtoPass;
import com.wildBirds.BlueChat.api.webSocket.controllers.MessageControllerWSR;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class UserFacade {

    private UserRepository userRep;
    private UserService userService;

    private MessageControllerWSR wsr;

    public List<UserDto> getUsers() {
        List<User> getUsers = userRep.findAll();
        List<Long> authorizatedSessionsIdentificators = wsr.getAuthorizatedSessionsIdentificators();
        List<UserDto> userDtoList = getUsers.stream()
                .map(user -> userService.toDto(user))
                .map(userDto -> {
                    if (authorizatedSessionsIdentificators.contains(userDto.getIdUser())) {
                        userDto.setActive(true);
                    }
                    return userDto;
                })
                .collect(Collectors.toList());

        return userDtoList;
    }

    public UserDto registerNewUser(UserDtoPass userDtoPass){
        User mappedUser = userService.toEntity(userDtoPass);
        User registeredUser = userRep.save(mappedUser);

        UserDto response = userService.toDto(registeredUser);
        return response;
    }

    public UserDto loginUser(UserDtoPass userDtoPass){

        User loginedUser = userRep.getUserByNickAndPassword(userDtoPass.getNick(), userDtoPass.getPassword());
        UserDto responseDto = userService.toDto(loginedUser);

        return responseDto;
    }
}
