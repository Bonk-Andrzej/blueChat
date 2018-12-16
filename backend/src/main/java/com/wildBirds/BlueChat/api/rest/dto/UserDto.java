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
    private boolean isActive;
//    private List<ChannelDto> channelsStaffed;


    @Override
    public String toString() {
        return "UserDto{" +
                "idUser=" + idUser +
                ", nick='" + nick + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
