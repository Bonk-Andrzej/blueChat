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
    public void getIdChanel() {

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

        Channel savedChanell = channelRepository.save(channel);

                //then

        Assert.assertNotNull(savedChanell.getIdChanel());
        Assert.assertEquals(channel.getName(), savedChanell.getName());
        Assert.assertEquals("Igor", savedChanell.getChannelOwner().getNick());

    }
}