package com.wildBirds.BlueChat.api.rest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class InvitationDto {

    private Long idInvitation;
    private UserDtoShort senderInvitation;
    private UserDtoShort receiverInvitation;
    private Instant dateInvitation;
}
