package com.wildBirds.BlueChat.domain.model;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import java.time.Instant;

public class ChannelsMessageRepositoryTest extends ConfigurationTest {

    private Logger logger = LoggerFactory.getLogger(ChannelsMessageRepositoryTest.class);

    @Test
    @Transactional
    public void shouldCreateNewChannelMessage() {

        logger.info("Running test >> shouldCreateNewChannelMessage");
        //given
        ChannelsMessage channelsMessage = new ChannelsMessage();

        User ownerChannel = new User();
        ownerChannel.setNick("IgorChannelMessage");
        ownerChannel.setPassword("password");
        ownerChannel = userRepository.save(ownerChannel);

        Channel channel = new Channel();
        channel.setName("general");
        channel.setChannelOwner(ownerChannel);
        channel = channelRepository.save(channel);

        User sender = new User();
        sender.setNick("mark");
        sender.setPassword("dsad");
        sender = userRepository.save(sender);

        String content = "Message in channel";

        Instant sentDate = Instant.now();

        //when
        channelsMessage.setChannel(channel);
        channelsMessage.setSender(sender);
        channelsMessage.setContent(content);
        channelsMessage.setSentDate(sentDate);
        ChannelsMessage savedMessage = chanMsgRep.save(channelsMessage);
        //then
        Assert.assertEquals(savedMessage.getContent(), "Message in channel");

    }
}