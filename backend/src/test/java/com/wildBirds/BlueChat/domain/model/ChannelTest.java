package com.wildBirds.BlueChat.domain.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ChannelTest {

    @Autowired
    ChannelRepository channelRepository;

    @Autowired
    UserRepository userRepository;
    @Test
    public void shouldCreateNewChanel() {

        //given
        User user = new User();
        user.setNick("Igor");
        user.setPassword("password");
        user.setPassword("somepassword");
        user = userRepository.save(user);

        Channel channel = new Channel();
        channel.setName("general");
        channel.setChannelOwner(user);
        //when

        Channel savedChannel = channelRepository.save(channel);

                //then

        Assert.assertNotNull(savedChannel.getIdChanel());
        Assert.assertEquals(channel.getName(), savedChannel.getName());
        Assert.assertEquals("Igor", savedChannel.getChannelOwner().getNick());

    }
    @Test
    public void shouldAddUserToChannelToExistingChannel(){

        //given
        User ownerChannel = new User();
        ownerChannel.setNick("Igor");
        ownerChannel.setPassword("password");
        ownerChannel.setPassword("somepassword");
        ownerChannel = userRepository.save(ownerChannel);

        User interlocutor = new User();
        interlocutor.setNick("Mark");
        interlocutor.setPassword("somePass");
        interlocutor = userRepository.save(interlocutor);

        Channel channel = new Channel();
        channel.setName("general");
        channel.setChannelOwner(ownerChannel);

        Channel savedChannel = channelRepository.save(channel);
        //when

        savedChannel.getUsersInChannel().add(interlocutor);

        //then
        Assert.assertEquals(savedChannel.getUsersInChannel().get(0).getNick(), interlocutor.getNick() );

    }
}