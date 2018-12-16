package com.wildBirds.BlueChat.domain.model;

import org.junit.Assert;
import org.junit.Test;

import java.time.Instant;


//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ActiveProfiles("test")
public class ChannelsMessageTest extends ConfigurationTest {

    @Test
    public void shouldCreateNewChannelMessage() {

        //given
        ChannelsMessage channelsMessage = new ChannelsMessage();

        User ownerChannel = new User();
        ownerChannel.setNick("Igor");
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