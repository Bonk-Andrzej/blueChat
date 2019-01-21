package com.wildBirds.BlueChat.domain.model;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import java.util.List;

public class ChannelRepositoryTest extends ConfigurationTest {

    private Logger logger = LoggerFactory.getLogger(ChannelRepositoryTest.class);

    @Test
    public void shouldCreateNewChanel() {
        logger.info("Running test >> shouldCreateNewChanel");
        //given
        User user = new User();
        user.setNick("MilenaChannel1");
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
        Assert.assertEquals("MilenaChannel1", savedChannel.getChannelOwner().getNick());

    }
    @Test
    public void shouldAddUserToChannelToExistingChannel(){
        logger.info("Running test >> shouldAddUserToChannelToExistingChannel");
        //given
        User ownerChannel = new User();
        ownerChannel.setNick("IgorChannel3");
        ownerChannel.setPassword("password");
        ownerChannel.setPassword("somepassword");
        ownerChannel = userRepository.save(ownerChannel);

        User interlocutor = new User();
        interlocutor.setNick("MarkChannel3");
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

    @Test
    @Transactional
    public void shouldReturnShortListOfChannelsOnlyWithNameAndIdAndPhoto() {
        //given
        logger.info("Running test >> shouldReturnShortListOfChannelsOnlyWithNameAndId");
        User user = new User();
        user.setNick("MilenaChannel33");
        user.setPassword("password");

        user = userRepository.save(user);

        Photo photo = new Photo();
        photo.setPhoto("rgb(23,151,11)");

        photo = photoRepository.save(photo);

        Channel channel = new Channel();
        channel.setName("general33");
        channel.setChannelOwner(user);
        channel.setProfilePhoto(photo);


        Photo photo2 = new Photo();
        photo2.setPhoto("rgb(33,27,29)");

        photo2 = photoRepository.save(photo2);
        Channel channel2 = new Channel();
        channel2.setName("general34");
        channel2.setChannelOwner(user);
        channel2.setProfilePhoto(photo2);

        channelRepository.saveMessage(channel);
        channelRepository.saveMessage(channel2);

        //when
        List<Channel> listNameAndId = channelRepository.getListNameAndId();

        Channel channel1 = listNameAndId.get(0);

        //then
        Assert.assertNull(channel1.getChannelOwner());
        Assert.assertFalse(channel1.getIsPublic());
        Assert.assertNotNull(channel1.getProfilePhoto());
        Assert.assertNotNull(channel1.getName());
        Assert.assertNotNull(channel1.getIdChannel());

        if (listNameAndId.size() >= 2) {
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false);
        }


    }
}