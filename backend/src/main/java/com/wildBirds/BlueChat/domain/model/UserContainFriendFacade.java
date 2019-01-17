package com.wildBirds.BlueChat.domain.model;


import com.wildBirds.BlueChat.api.rest.dto.FriendsDto;

import java.util.List;
import java.util.stream.Collectors;

public class UserContainFriendFacade {

    private UserContainFriendService service;
    private UserContainFriendRepository repository;

    public UserContainFriendFacade(UserContainFriendService userContainFriendService, UserContainFriendRepository repository) {
        this.service = userContainFriendService;
        this.repository = repository;
    }

    public List<FriendsDto> getUserContainFriend(Long idUser) {

        List<UserContainFriend> userFriendship = repository.getUserFriendship(idUser);
        List<FriendsDto> friendsDtoList = userFriendship.stream()
                .map(userContainFriend -> service.toDto(idUser, userContainFriend))
                .collect(Collectors.toList());

        return friendsDtoList;
    }

    public FriendsDto addFriendship(Long idUser, FriendsDto friendsDto) {
        UserContainFriend userContainFriend = service.toEntity(idUser, friendsDto);

        UserContainFriend saveEntity = repository.saveUserContainFriends(userContainFriend);

        return service.toDto(idUser, saveEntity);

    }

    public void remove(FriendsDto friendsDto){
        repository.deleteByIdUserContainFriend(friendsDto.getIdFriendship());
    }

}
