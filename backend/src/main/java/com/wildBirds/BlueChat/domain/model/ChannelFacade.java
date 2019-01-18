package com.wildBirds.BlueChat.domain.model;


import com.wildBirds.BlueChat.api.rest.dto.ChannelDto;
import com.wildBirds.BlueChat.api.rest.dto.ChannelDtoShort;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ChannelFacade {

    private ChannelRepository channelRepository;
    private ChannelService channelService;
    private UserRepository userRepository;

    public ChannelDto addChannel(ChannelDto channelDto){
        Channel channel = channelService.toEntity(channelDto);

        Channel addedChannel = channelRepository.saveMessage(channel);

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

        Set<User> usersInChannel = channel.getUsersInChannel();

        usersInChannel.add(user);

        channel.setUsersInChannel(usersInChannel);

        channel = channelRepository.save(channel);
        ChannelDto resposne = channelService.toDto(channel);
        return resposne;
    }

    // TODO: 19.12.2018 have to write tests
    public List<ChannelDto> getChannels(){

        List<Channel> channels = channelRepository.findAll();

        List<ChannelDto> channelDtoList = channels.stream()
                .map(channel -> channelService.toDto(channel))
                .collect(Collectors.toList());

        return channelDtoList;
    }

    public List<ChannelDtoShort> getChannelsShort() {
        List<Channel> channelList = channelRepository.getListNameAndId();
        List<ChannelDtoShort> channelDtoShortList = channelList.stream()
                .map(channel -> channelService.toDtoShort(channel))
                .collect(Collectors.toList());

        return channelDtoShortList;
    }

    // TODO: 18.01.2019 have to write tests
    public  ChannelDto getById(Long id){
        Channel channel = channelRepository.getOne(id);
        return channelService.toDto(channel);
    }
}
