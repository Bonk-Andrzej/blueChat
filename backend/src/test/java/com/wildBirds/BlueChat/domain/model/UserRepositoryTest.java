package com.wildBirds.BlueChat.domain.model;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserRepositoryTest extends ConfigurationTest {

    private Logger logger = LoggerFactory.getLogger(UserRepositoryTest.class);

    @Test
    public void shouldCreateNewUser() {

        logger.info("Running test >> shouldCreateNewUser");
        //given
        User user = new User();
        user.setNick("IgorUser");
        user.setPassword("12345");

        //when
        User savedUser = userRepository.save(user);

        //then
        Assert.assertNotNull(savedUser.getIdUser());
        Assert.assertEquals(savedUser.getNick(), user.getNick());
        Assert.assertEquals(savedUser.getPassword(), user.getPassword());
    }
}
