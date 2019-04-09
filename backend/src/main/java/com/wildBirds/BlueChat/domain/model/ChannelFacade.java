package com.wildBirds.BlueChat.domain.model;


import com.wildBirds.BlueChat.api.rest.dto.ChannelDto;
import com.wildBirds.BlueChat.api.rest.dto.ChannelDtoCreate;
import com.wildBirds.BlueChat.api.rest.dto.ChannelDtoShort;
import com.wildBirds.BlueChat.api.rest.dto.UserDtoShort;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ChannelFacade {

    private ChannelRepository channelRepository;
    private ChannelService channelService;
    private UserRepository userRepository;
    private UserService userService;

    public ChannelDto addChannel(ChannelDtoCreate channelDtoCreate){
        // TODO: 4/2/2019 HAVE TO FINISH IMPLEMENTATION
//        ChannelDto channelDto = new ChannelDto();
        Channel channel = channelService.toEntity(channelDtoCreate);

        Channel addedChannel = channelRepository.saveChannel(channel);

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

    public ChannelDto removeUserFromChannel(Long userId, Long  channelId){

        Channel channel = channelRepository.getOne(channelId);
        User user = userRepository.getOne(userId);
        channel.getUsersInChannel().remove(user);
        return channelService.toDto(channel);
    }

    public List<ChannelDtoShort> getChannelsShort() {
        List<Channel> channelList = channelRepository.getPublicChannels();
        List<ChannelDtoShort> channelDtoShortList = channelList.stream()
                .map(channel -> channelService.toDtoShort(channel))
                .collect(Collectors.toList());

        return channelDtoShortList;
    }

    public List<ChannelDtoShort> getChannelsByPhrase(String phrase){
        List<Channel> channelList = channelRepository.getByPhrase(phrase);

        return channelList.stream()
                .map(channel -> channelService.toDtoShort(channel))
                .collect(Collectors.toList());
    }

    // TODO: 18.01.2019 have to write tests
    public  ChannelDto getById(Long id){
        Channel channel = channelRepository.getOne(id);
        return channelService.toDto(channel);
    }

    public List<ChannelDtoShort> getChannelsShort(Long idUser){
        List<Channel> channels = channelRepository.getChannels(idUser);

        List<ChannelDtoShort> result = channels.stream()
                .map(channel -> channelService.toDtoShort(channel))
                .collect(Collectors.toList());
        return result;
    }

    public List<UserDtoShort> getChannelsMembers(Long channelId){
        return channelRepository.getChannelMembers(channelId).stream()
                .map(user -> userService.toDtoShort(user))
                .collect(Collectors.toList());
    }

}
