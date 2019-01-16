package com.wildBirds.BlueChat.domain.model;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChannelRepositoryTest extends ConfigurationTest {

    private Logger logger = LoggerFactory.getLogger(ChannelRepositoryTest.class);

    @Test
    public void shouldCreateNewChanel() {
        logger.info("Running test >> shouldCreateNewChanel");
        //given
        User user = new User();
        user.setNick("Milena");
        user.setPassword("password");

        user = userRepository.save(user);

        Channel channel = new Channel();
        channel.setName("general");
        channel.setChannelOwner(user);

        //when
        Channel savedChannel = channelRepository.saveMessage(channel);

        //then
        Assert.assertNotNull(savedChannel.getIdChannel());
        Assert.assertEquals(channel.getName(), savedChannel.getName());
        Assert.assertEquals("Milena", savedChannel.getChannelOwner().getNick());

    }
    @Test
    public void shouldAddUserToChannelToExistingChannel(){
        logger.info("Running test >> shouldAddUserToChannelToExistingChannel");
        //given
        User ownerChannel = new User();
        ownerChannel.setNick("IgorChannel");
        ownerChannel.setPassword("password");
        ownerChannel.setPassword("somepassword");
        ownerChannel = userRepository.save(ownerChannel);

        User interlocutor = new User();
        interlocutor.setNick("MarkChannel");
        interlocutor.setPassword("somePass");
        interlocutor = userRepository.save(interlocutor);

        Channel channel = new Channel();
        channel.setName("general");
        channel.setChannelOwner(ownerChannel);

        Channel savedChannel = channelRepository.saveMessage(channel);
        //when

        savedChannel.getUsersInChannel().add(interlocutor);

        //then
        Assert.assertEquals(savedChannel.getUsersInChannel().iterator().next().getNick(), interlocutor.getNick() );

    }
}