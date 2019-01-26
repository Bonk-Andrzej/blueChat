package com.wildBirds.BlueChat.domain.model;


import com.wildBirds.BlueChat.api.rest.dto.UserDto;
import com.wildBirds.BlueChat.api.rest.dto.UserDtoPass;
import com.wildBirds.BlueChat.api.rest.dto.UserDtoShort;


class UserService {


    private PhotoService photoService;

    public UserService(PhotoService photoService) {
        this.photoService = photoService;
    }

    public User toEntity(UserDto userDto) {
        User user = new User();
        if (userDto.getIdUser() != null) {
            user.setIdUser(userDto.getIdUser());
        }
        if (userDto.getPhotoDto() != null) {
            user.setProfilePhoto(photoService.toEntity(userDto.getPhotoDto()));
        }
        user.setNick(userDto.getNick());
        user.setDescription(userDto.getDescription());
        user.setEmail(userDto.getEmail());
        return user;
    }

    public User toEntity(UserDtoPass userDtoPass) {
        User user = new User();
        user.setNick(userDtoPass.getNick());
        user.setPassword(userDtoPass.getPassword());

        return user;
    }
    public User toEntity(UserDtoShort userDtoShort){
        User user = new User();
        user.setIdUser(userDtoShort.getIdUser());
        user.setNick(userDtoShort.getNick());
        user.setProfilePhoto(photoService.toEntity(userDtoShort.getPhotoDto()));
        return user;
    }

    public UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        if (user.getIdUser() != null) {
            userDto.setIdUser(user.getIdUser());
        }
        if (user.getProfilePhoto() != null) {
            userDto.setPhotoDto(photoService.toDto(user.getProfilePhoto()));
        }

        userDto.setNick(user.getNick());
        userDto.setDescription(user.getDescription());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    public UserDtoShort toDtoShort(User user){
        UserDtoShort userDtoShort = new UserDtoShort();

        userDtoShort.setIdUser(user.getIdUser());
        userDtoShort.setNick(user.getNick());
        userDtoShort.setPhotoDto(photoService.toDto(user.getProfilePhoto()));

        return userDtoShort;

    }

    public UserDtoShort toEntity(User user){
        UserDtoShort userDtoShort = new UserDtoShort();

        if (user.getIdUser() != null){
            userDtoShort.setIdUser(user.getIdUser());
        }
        userDtoShort.setNick(user.getNick());

        return userDtoShort;
    }


}
