package com.wildBirds.BlueChat.api.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long idUser;
    private String nick;
    private String email;
    private String description;
    private PhotoDto photoDto;



}
