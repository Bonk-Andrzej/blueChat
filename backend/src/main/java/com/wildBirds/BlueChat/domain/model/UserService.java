package com.wildBirds.BlueChat.domain.model;


import com.wildBirds.BlueChat.api.rest.dto.UserDto;
import com.wildBirds.BlueChat.api.rest.dto.UserDtoPass;
import com.wildBirds.BlueChat.api.rest.dto.UserDtoShort;
import org.springframework.beans.factory.annotation.Autowired;


public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User toEntity(UserDto userDto) {
        User user = new User();
        if (userDto.getIdUser() != null) {
            user.setIdUser(userDto.getIdUser());
            user = userRepository.getOne(userDto.getIdUser());
        }
        user.setNick(userDto.getNick());
        return user;
    }

    public User toEntity(UserDtoPass userDtoPass) {
        User user = new User();
        user.setNick(userDtoPass.getNick());
        user.setPassword(userDtoPass.getPassword());

        return user;
    }

    public UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        if (user.getIdUser() != null) {
            userDto.setIdUser(user.getIdUser());
        }
        userDto.setNick(user.getNick());
        return userDto;
    }

    public UserDtoShort toEntity(User user){
        UserDtoShort userDtoShort = new UserDtoShort();

        if (user.getIdUser() != null){
            userDtoShort.setIdUser(user.getIdUser());
        }
        userDtoShort.setName(user.getNick());

        return userDtoShort;
    }
}
