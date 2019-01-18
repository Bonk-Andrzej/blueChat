package com.wildBirds.BlueChat.api.rest.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ChannelDto {
    private Long idChannel;
    private String name;
    private boolean isPublic;
    private UserDtoShort channelOwner;
    private List<UserDto> userList = new ArrayList<>();
    private PhotoDto photoDto;

    public boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

}
