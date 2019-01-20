package com.wildBirds.BlueChat.domain.model;


import com.wildBirds.BlueChat.api.rest.dto.FriendsDto;
import com.wildBirds.BlueChat.api.webSocket.controllers.MessageControllerWSR;
import com.wildBirds.BlueChat.api.webSocket.types.RemoteProcedure;
import com.wildBirds.WebSocketRpc.domain.model.Session;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserContainFriendFacade {

    private UserContainFriendService service;
    private UserContainFriendRepository repository;
    private MessageControllerWSR wsr;

    public UserContainFriendFacade( MessageControllerWSR wsr ,UserContainFriendService userContainFriendService, UserContainFriendRepository repository) {
        this.service = userContainFriendService;
        this.repository = repository;
        this.wsr = wsr;
    }

    public List<FriendsDto> getUserContainFriend(Long idUser) {


        Map<Long, Session<RemoteProcedure, Long>> authorizedSessionsIdentifications = wsr.getAuthorizedSessionsIdentifications();

        List<UserContainFriend> userFriendship = repository.getUserFriendship(idUser);
        List<FriendsDto> friendsDtoList = userFriendship.stream()
                .map(userContainFriend -> service.toDto(idUser, userContainFriend))
                .map(userDto -> {
                    if (authorizedSessionsIdentifications.containsKey(userDto.getFriend().getIdUser())) {
                        userDto.getFriend().setActive(true);
                    }
                    return userDto;
                })
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
