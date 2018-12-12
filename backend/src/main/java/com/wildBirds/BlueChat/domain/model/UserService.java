package com.wildBirds.BlueChat.domain.model;

import com.wildBirds.BlueChat.api.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;


public class UserService {

    public User toEntity(UserDto userDto) {
        User user = new User();
        if (userDto.getIdUser() == null) {
            user.setIdUser(userDto.getIdUser());
        }
        user.setNick(userDto.getNick());
        return user;
    }

    public UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setIdUser(user.getIdUser());
        userDto.setNick(user.getNick());
        return userDto;
    }
}
