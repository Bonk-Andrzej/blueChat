package com.wildBirds.BlueChat.domain.model;


import com.wildBirds.BlueChat.api.rest.dto.FriendsDto;

import java.util.List;
import java.util.stream.Collectors;

public class UserContainFriendFacade {

    private UserContainFriendService userContainFriendService;
    private UserContainFriendRepository repository;

    public UserContainFriendFacade(UserContainFriendService userContainFriendService, UserContainFriendRepository repository) {
        this.userContainFriendService = userContainFriendService;
        this.repository = repository;
    }

    public List<FriendsDto> getUserContainFriend(Long idUser) {

        List<UserContainFriend> userFriendship = repository.getUserFriendship(idUser);
        List<FriendsDto> friendsDtoList = userFriendship.stream()
                .map(userContainFriend -> userContainFriendService.toDto(idUser, userContainFriend))
                .collect(Collectors.toList());

        return friendsDtoList;
    }
    public FriendsDto add(FriendsDto friendsDto){
        return null;

    }
    public void remove(FriendsDto friendsDto){

    }

}
