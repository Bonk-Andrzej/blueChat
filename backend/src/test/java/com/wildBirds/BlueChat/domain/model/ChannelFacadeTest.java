package com.wildBirds.BlueChat.domain.model;

import com.wildBirds.BlueChat.api.rest.dto.ChannelDto;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;

import javax.transaction.Transactional;
import java.util.List;

public class ChannelFacadeTest extends ConfigurationTest{

    private Logger logger = LoggerFactory.getLogger(ChannelFacadeTest.class);

    @Test
    public void shouldAddNewChannel() {
        logger.info("Running test >> shouldCreateNewChanel");
        //before
        User user = new User();
        user.setNick("MilenaOwner");
        user.setPassword("password1234");

        user = userRepository.save(user);

        //given
        Long idUser = user.getIdUser();
        String channelName = "task force";

        ChannelDto channelDto = new ChannelDto(null, channelName, idUser, null, false);
        //when
        ChannelDto savedChannel = channelFacade.addChannel(channelDto);


        //then
        Assert.assertNotNull(savedChannel.getIdChannel());
        Assert.assertEquals(channelName, savedChannel.getName());
        Assert.assertEquals(idUser, savedChannel.getUserIdChannelOwner());

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

        Long idUser = user.getIdUser();
        String channelName = "task force";
        ChannelDto channelDto = new ChannelDto(null, channelName, idUser, null, false);
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

        Long idUser = owner.getIdUser();
        String channelName = "task force";
        ChannelDto channelDto = new ChannelDto();
        channelDto.setName(channelName);
        channelDto.setUserIdChannelOwner(idUser);
        ChannelDto savedChannel = channelFacade.addChannel(channelDto);
        Long idChannel = savedChannel.getIdChannel();

        //when
        channelFacade.addUserToChannel(addingUser.getIdUser(), idChannel);


        //then
        List<User> usersInChannel = channelRepository.getOne(idChannel).getUsersInChannel();
        Assert.assertEquals(addingUser.getIdUser(), usersInChannel.get(0).getIdUser());

    }
}