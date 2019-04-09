package com.wildBirds.BlueChat.api.rest.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {

    private Long idUser;
    private String nick;
    private String email;
    private String description;
    private PhotoDto photoDto;



}
