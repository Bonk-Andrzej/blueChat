package com.wildBirds.BlueChat.domain.model;


import com.wildBirds.BlueChat.api.rest.dto.FriendsDto;
import com.wildBirds.BlueChat.api.rest.dto.InvitationDto;

import java.util.List;
import java.util.stream.Collectors;

public class InvitationFacade {
    private InvitationRepository repository;
    private InvitationService service;
    private UserContainFriendFacade friendFacade;

    public InvitationFacade(InvitationRepository repository, InvitationService service, UserContainFriendFacade friendFacade) {
        this.repository = repository;
        this.service = service;
        this.friendFacade = friendFacade;
    }

    public InvitationDto saveInvitation(InvitationDto invitationDto) {

        Invitation invitation = service.toEntity(invitationDto);
        Invitation save = repository.saveInvitation(invitation);
        return service.toDto(save);

    }

    public List<InvitationDto> getUserInvitation(Long idUser) {

        List<Invitation> usersInvitation = repository.getInvitationByReceiverInvitationIdUser(idUser);
        return usersInvitation.stream()
                .map(invitation -> service.toDto(invitation))
                .collect(Collectors.toList());

    }

    public FriendsDto acceptInvitation(InvitationDto invitationDto) {
        Long idUser = invitationDto.getReceiverInvitation().getIdUser();
        Long idFriend = invitationDto.getSenderInvitation().getIdUser();
        FriendsDto friendsDto = friendFacade.addFriendship(idUser, idFriend);
//        Invitation invitation = service.toEntity(invitationDto);
        Invitation toRemove = repository.getOne(invitationDto.getIdInvitation());
        repository.delete(toRemove);

        return friendsDto;
    }

}
