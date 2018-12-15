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
public class UserTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void shouldCreateNewUser() {

        //given
        User user = new User();

        user.setNick("Igor");
        user.setPassword("12345");
        //when

        User savedUser = userRepository.save(user);
        //then

        Assert.assertNotNull(savedUser.getIdUser());
        Assert.assertEquals(savedUser.getNick(), user.getNick());
        Assert.assertEquals(savedUser.getPassword(), user.getPassword());
    }
}
