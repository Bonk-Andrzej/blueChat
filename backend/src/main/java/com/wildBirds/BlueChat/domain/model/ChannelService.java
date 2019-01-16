package com.wildBirds.BlueChat.domain.model;


import com.wildBirds.BlueChat.api.rest.dto.ChannelDto;
import com.wildBirds.BlueChat.api.rest.dto.UserDto;
import com.wildBirds.BlueChat.domain.model.exceptions.ChannelServiceExceptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;


class ChannelService {

    private Logger log = LoggerFactory.getLogger(MessageService.class);
    private UserService userService;

    public ChannelService(UserService userService) {
        this.userService = userService;
    }

    protected Channel toEntity(ChannelDto channelDto){
        Channel channel = new Channel();

        try {
            if (channelDto.getIdChannel() != null) {
                channel.setIdChannel(channelDto.getIdChannel());
            }
            channel.setName(channelDto.getName());
            channel.setIsPublic(channelDto.getIsPublic());

            User user = new User();
            user.setIdUser(channelDto.getUserIdChannelOwner());

            channel.setChannelOwner(user);

            channel.setUsersInChannel(new HashSet<>());

            if (channelDto.getUserList() != null) {
                List<User> users = channelDto.getUserList().stream()
                        .map(userDto -> userService.toEntity(userDto))
                        .collect(Collectors.toList());
                users.stream().forEach(user1 -> channel.getUsersInChannel().add(user1));
            }
        } catch (NullPointerException e) {
            log.error("Channel Service- Some field was null", e.getMessage());
            throw new ChannelServiceExceptions("Channel Service - Some field was null");

        } catch (Exception e) {
            log.error("Channel Service Exception", e.getMessage());
            throw new ChannelServiceExceptions("Channel Service Exception");
        }


        return channel;
    }
    protected ChannelDto toDto(Channel channel){
        ChannelDto channelDto = null;
        try {
            channelDto = new ChannelDto();

            channelDto.setIdChannel(channel.getIdChannel());
            channelDto.setName(channel.getName());
            channelDto.setUserIdChannelOwner(channel.getChannelOwner().getIdUser());
            channelDto.setIsPublic(channel.getIsPublic());
            if (channel.getUsersInChannel() != null){
                List<UserDto> userDtos = channel.getUsersInChannel().stream()
                        .map(user -> userService.toDto(user))
                        .collect(Collectors.toList());
                channelDto.setUserList(userDtos);
            }
        } catch (NullPointerException e) {
            log.error("Channel Service- Some field was null", e.getMessage());
            throw new ChannelServiceExceptions("Channel Service - Some field was null");

        } catch (Exception e) {
            log.error("Channel Service Exception", e.getMessage());
            throw new ChannelServiceExceptions("Channel Service Exception");
        }
        return channelDto;
    }
}
