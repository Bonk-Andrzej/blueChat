package com.wildBirds.BlueChat.domain.model;

import org.junit.Assert;
import org.junit.Test;

public class UserTest  extends ConfigurationTest {


    @Test
    public void shouldCreateNewUser() {

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
