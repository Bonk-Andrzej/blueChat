package com.wildBirds.BlueChat.domain.model;


import com.wildBirds.BlueChat.api.rest.dto.ChannelDto;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ChannelFacade {

    private ChannelRepository channelRepository;
    private ChannelService channelService;
    private UserRepository userRepository;

    public ChannelDto addChannel(ChannelDto channelDto){
        Channel channel = channelService.toEntity(channelDto);

        Channel addedChannel = channelRepository.save(channel);

        ChannelDto response = channelService.toDto(addedChannel);

        return response;
    }
    public void removeChannel(ChannelDto channelDto){

        Channel channel = channelService.toEntity(channelDto);
        channelRepository.delete(channel);

    }
    public ChannelDto addUserToChannel(Long userId, Long  channelId) {

        Channel channel = channelRepository.getOne(channelId);
        User user = userRepository.getOne(userId);

        List<User> usersInChannel = channel.getUsersInChannel();

        usersInChannel.add(user);

        channel.setUsersInChannel(usersInChannel);

        channel = channelRepository.save(channel);
        ChannelDto resposne = channelService.toDto(channel);
        return resposne;
    }
}
