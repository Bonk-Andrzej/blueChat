package com.wildBirds.BlueChat.domain.model;

import com.wildBirds.BlueChat.api.rest.dto.ChannelDto;
import com.wildBirds.BlueChat.api.rest.dto.ChannelsMessageDto;
import com.wildBirds.BlueChat.api.rest.dto.UserDto;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;

public class ChannelsMessageFacadeTest extends ConfigurationTest{

    private Logger logger = LoggerFactory.getLogger(ChannelsMessageFacadeTest.class);

    @Test
    @Transactional
    public void shouldAddNewMessage() {
        //before
        //create necessary channel
        Channel channel = new Channel();
        channel.setName("TestMessageFacade");
        channel.setIsPublic(true);
        channel.setChannelOwner(new User("UserMessageFacade", "password"));

        channel = channelRepository.save(channel);

        //create necessary message sender
        User sender = new User("PabloSender", "somePass");
        sender = userRepository.save(sender);

        //given
        ChannelService channelService = new ChannelService(new UserService());
        ChannelsMessageDto channelsMessageDto = new ChannelsMessageDto();
        UserService userService = new UserService();

        String content = "Some message to channel";
        UserDto senderDto = userService.toDto(sender);

        ChannelDto channelDto = channelService.toDto(channel);

        channelsMessageDto.setChannelId(channelDto.getIdChannel());
        channelsMessageDto.setContent(content);
        channelsMessageDto.setSenderId(senderDto.getIdUser());
        channelsMessageDto.setSentDate(Instant.now());

        //when
        ChannelsMessageDto addedMessages = channelsMessageFacade.saveMessage(channelsMessageDto);

        //then
        Assert.assertNotNull(addedMessages.getIdChannelsMessageDto());
        Assert.assertEquals(channel.getIdChannel(), addedMessages.getChannelId());
        Assert.assertEquals(sender.getIdUser(), addedMessages.getSenderId());
        Assert.assertEquals(content, addedMessages.getContent());
    }

    @Test
    @Transactional
    public void shouldHetConversation() {
        //before
        //create necessary channel
        User owner = new User("UserMessageConversation", "password");
        owner = userRepository.save(owner);

        Channel channel = new Channel();
        channel.setName("TestConversation");
        channel.setIsPublic(true);
        channel.setChannelOwner(owner);

        channel = channelRepository.save(channel);

        //create necessary message sender
        User sender = new User("PabloSenderConversation", "somePass");
        sender = userRepository.save(sender);

        ChannelService channelService = new ChannelService(new UserService());
        ChannelsMessageDto channelsMessageDto = new ChannelsMessageDto();
        UserService userService = new UserService();

        String content = "Some message to channel";
        UserDto senderDto = userService.toDto(sender);

        ChannelDto channelDto = channelService.toDto(channel);

        channelsMessageDto.setChannelId(channelDto.getIdChannel());
        channelsMessageDto.setContent(content);
        channelsMessageDto.setSenderId(senderDto.getIdUser());
        channelsMessageDto.setSentDate(Instant.now());
        //given

        ChannelsMessageDto addedMessages1 = channelsMessageFacade.saveMessage(channelsMessageDto);
        ChannelsMessageDto addedMessages2 = channelsMessageFacade.saveMessage(channelsMessageDto);
        Long idChannel = channel.getIdChannel();
        Integer limit = 10;
        Integer toBound = 0;
//        //when
        List<ChannelsMessageDto> conversation = channelsMessageFacade.getConversation(idChannel, limit, toBound);

//        //then
        Assert.assertEquals(content, conversation.get(0).getContent());
        Assert.assertEquals(content, conversation.get(1).getContent());
        Assert.assertEquals(2, conversation.size());
    }
}