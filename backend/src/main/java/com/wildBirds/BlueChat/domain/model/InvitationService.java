package com.wildBirds.BlueChat.domain.model;

import com.wildBirds.BlueChat.api.rest.dto.InvitationDto;

class InvitationService {

    private UserService userService;

    public InvitationService(UserService userService) {
        this.userService = userService;
    }

    public InvitationDto toDto(Invitation invitation) {
        InvitationDto invitationDto = new InvitationDto();

        if (invitation.getIdInvitation() != null) {

            invitationDto.setIdInvitation(invitation.getIdInvitation());
        }
        invitationDto.setDateInvitation(invitation.getDateInvitation());
        invitationDto.setReceiverInvitation(userService.toDtoShort(invitation.getReceiverInvitation()));
        invitationDto.setSenderInvitation(userService.toDtoShort(invitation.getSenderInvitation()));
        return invitationDto;
    }

    public Invitation toEntity(InvitationDto invitationDto) {
        Invitation invitation = new Invitation();

        if (invitationDto.getIdInvitation() != null) {
            invitation.setIdInvitation(invitationDto.getIdInvitation());
        }
        invitation.setDateInvitation(invitationDto.getDateInvitation());
        invitation.setReceiverInvitation(userService.toEntity(invitationDto.getReceiverInvitation()));
        invitation.setSenderInvitation(userService.toEntity(invitationDto.getSenderInvitation()));
        return invitation;
    }
}
