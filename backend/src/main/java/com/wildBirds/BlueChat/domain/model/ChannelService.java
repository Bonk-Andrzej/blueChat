package com.wildBirds.BlueChat.domain.model;


import com.wildBirds.BlueChat.api.rest.dto.ChannelDto;
import com.wildBirds.BlueChat.api.rest.dto.UserDto;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
class ChannelService {

     private UserService userService;


    protected Channel toEntity(ChannelDto channelDto){
        Channel channel = new Channel();

        if(channelDto.getIdChannel() != null){
            channel.setIdChannel(channelDto.getIdChannel());
        }
        channel.setName(channelDto.getName());
        channel.setPublic(channelDto.getIsPublic());

        User user = new User();
        user.setIdUser(channelDto.getUserIdChannelOwner());

        channel.setChannelOwner(user);

        channel.setUsersInChannel(new ArrayList<>());

         if (channelDto.getUserList() != null) {
             List<User> users = channelDto.getUserList().stream()
                     .map(userDto -> userService.toEntity(userDto))
                     .collect(Collectors.toList());
             users.stream().forEach(user1 -> channel.getUsersInChannel().add(user1));
         }
        return channel;
    }
    protected ChannelDto toDto(Channel channel){
        ChannelDto channelDto = new ChannelDto();

        channelDto.setIdChannel(channel.getIdChannel());
        channelDto.setName(channel.getName());
        channelDto.setUserIdChannelOwner(channel.getChannelOwner().getIdUser());
        channelDto.setIsPublic(channel.isPublic());
         if (channel.getUsersInChannel() != null){
             List<UserDto> userDtos = channel.getUsersInChannel().stream()
                     .map(user -> userService.toDto(user))
                     .collect(Collectors.toList());
             channelDto.setUserList(userDtos);
         }

        return channelDto;
    }
}
