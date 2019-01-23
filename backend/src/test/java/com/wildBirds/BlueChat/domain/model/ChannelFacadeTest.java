package com.wildBirds.BlueChat.domain.model;

import com.wildBirds.BlueChat.api.rest.dto.ChannelDto;
import com.wildBirds.BlueChat.api.rest.dto.ChannelDtoShort;
import com.wildBirds.BlueChat.api.rest.dto.UserDtoShort;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

public class ChannelFacadeTest extends ConfigurationTest{

    private Logger logger = LoggerFactory.getLogger(ChannelFacadeTest.class);

    @Test
    @Transactional
    public void shouldAddNewChannel() {
        logger.info("Running test >> shouldCreateNewChanel");
        //before
        User user = new User();
        user.setNick("MilenaOwner");
        user.setPassword("password1234");
        user = userRepository.save(user);

        //given
        UserDtoShort userDtoShort = new UserDtoShort();
        userDtoShort.setIdUser(user.getIdUser());
        userDtoShort.setNick(user.getNick());

        String channelName = "task force";

        ChannelDto channelDto = new ChannelDto();
        channelDto.setName(channelName);
        channelDto.setChannelOwner(userDtoShort);
        channelDto.setIsPublic(false);
        //when
        ChannelDto savedChannel = channelFacade.addChannel(channelDto);


        //then
        Assert.assertNotNull(savedChannel.getIdChannel());
        Assert.assertEquals(channelName, savedChannel.getName());
        Assert.assertEquals(userDtoShort, savedChannel.getChannelOwner());

    }

    @Test
    @Transactional
    public void shouldRemoveExcisingChannel() {
        logger.info("Running test >> shouldRemoveExcisingChannel");
        //before
        User user = new User();
        user.setNick("MilenaOwner2");
        user.setPassword("password1234");

        user = userRepository.save(user);

        // given

        UserDtoShort userDtoShort = new UserDtoShort();
        userDtoShort.setIdUser(user.getIdUser());
        userDtoShort.setNick(user.getNick());


        String channelName = "task force";
        ChannelDto channelDto = new ChannelDto();
        channelDto.setName(channelName);
        channelDto.setChannelOwner(userDtoShort);
        channelDto.setIsPublic(false);
        ChannelDto savedChannel = channelFacade.addChannel(channelDto);
        Long idChannel = savedChannel.getIdChannel();

        //when
        channelFacade.removeChannel(savedChannel);
        User excistingUser = userRepository.getOne(user.getIdUser());


        try {
            channelRepository.getOne(idChannel);
            Assert.assertTrue(false);
        } catch (JpaObjectRetrievalFailureException e) {
            Assert.assertTrue(true);
        }
        Assert.assertEquals(user.getNick(), excistingUser.getNick());
        Assert.assertEquals(user.getIdUser(), excistingUser.getIdUser());


    }

    @Test
    @Transactional
    public void shouldAddUserToExistingChannel() {
        logger.info("Running test >> shouldRemoveExcisingChannel");
        //before
        User owner = new User();
        owner.setNick("MilenaOwner2");
        owner.setPassword("password1234");
        owner = userRepository.save(owner);

        User addingUser = new User();
        addingUser.setNick("IgorChannelTest");
        addingUser.setPassword("somePassword");
        addingUser = userRepository.save(addingUser);

        // given
        UserDtoShort userDtoShort = new UserDtoShort();
        userDtoShort.setIdUser(owner.getIdUser());
        userDtoShort.setNick(owner.getNick());

        String channelName = "task force";
        ChannelDto channelDto = new ChannelDto();
        channelDto.setName(channelName);
        channelDto.setChannelOwner(userDtoShort);
        ChannelDto savedChannel = channelFacade.addChannel(channelDto);
        Long idChannel = savedChannel.getIdChannel();

        //when
        channelFacade.addUserToChannel(addingUser.getIdUser(), idChannel);


        //then
        Set<User> usersInChannel = channelRepository.getOne(idChannel).getUsersInChannel();

        Assert.assertEquals(addingUser.getIdUser(), usersInChannel.iterator().next().getIdUser());

    }

    @Test
    @Transactional
    public void shouldRemoveUserFromExistingChannel() {
        logger.info("Running test >> shouldRemoveUserFromExistingChannel");
        //before
        User owner = new User();
        owner.setNick("MilenaOwner5");
        owner.setPassword("password1234");
        owner = userRepository.save(owner);

        User removingUser = new User();
        removingUser.setNick("IgorChannelTest");
        removingUser.setPassword("somePassword");
        removingUser = userRepository.save(removingUser);

        // given
        UserDtoShort userDtoShort = new UserDtoShort();
        userDtoShort.setIdUser(owner.getIdUser());
        userDtoShort.setNick(owner.getNick());

        String channelName = "task force";
        ChannelDto channelDto = new ChannelDto();
        channelDto.setName(channelName);
        channelDto.setChannelOwner(userDtoShort);
        ChannelDto savedChannel = channelFacade.addChannel(channelDto);
        Long idChannel = savedChannel.getIdChannel();
        channelFacade.addUserToChannel(removingUser.getIdUser(), idChannel);

        //when
        channelFacade.removeUserFromChannel(removingUser.getIdUser(), idChannel);

        //then
        Set<User> usersInChannel = channelRepository.getOne(idChannel).getUsersInChannel();

        Assert.assertTrue(usersInChannel.isEmpty());

    }

    @Test
    @Transactional
    public void shouldReturnShortListOfChannelsOnlyWithNameAndId() {
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

        channelRepository.saveChannel(channel);
        channelRepository.saveChannel(channel2);

        //when
        List<ChannelDtoShort> listNameAndId = channelFacade.getChannelsShort();

        ChannelDtoShort channel1 = listNameAndId.get(0);

        //then
        Assert.assertNotNull(channel1.getPhotoDto());
        Assert.assertNotNull(channel1.getName());
        Assert.assertNotNull(channel1.getIdChannel());

        if (listNameAndId.size() >= 2) {
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false);
        }


    }


}