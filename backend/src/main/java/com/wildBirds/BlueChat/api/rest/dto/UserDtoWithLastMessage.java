package com.wildBirds.BlueChat.api.rest.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDtoWithLastMessage {
    private Long idUser ;
    private String nick;
    private PhotoDto photoDto;
    private MessageDto lastMessage;


}
