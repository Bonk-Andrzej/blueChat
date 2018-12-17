package com.wildBirds.BlueChat.domain.model;


import com.wildBirds.BlueChat.api.rest.dto.ChannelDto;
import lombok.AllArgsConstructor;

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
        channel.getUsersInChannel().add(user);
        channel = channelRepository.save(channel);
        ChannelDto resposne = channelService.toDto(channel);
        return resposne;
    }
}
