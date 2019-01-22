package com.wildBirds.BlueChat.domain.model;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import java.util.List;

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

    @Test
    @Transactional
    public void shouldGetUserByPhrase() {

        logger.info("Running test >> shouldGetUserByPhrase");
        //given
        User user = new User();
        user.setNick("aaaa");
        user.setPassword("12345");

        userRepository.save(user);
        //when

        //given
        User user1 = new User();
        user1.setNick("abaaab");
        user1.setPassword("12345");

        userRepository.save(user1);

        //given
        User user2 = new User();
        user2.setNick("vvvv");
        user2.setPassword("12345");

        userRepository.save(user2);

        //given
        User user3 = new User();
        user3.setNick("ccccc");
        user3.setPassword("12345");

        userRepository.save(user3);

        //given
        User user4 = new User();
        user4.setNick("bba");
        user4.setPassword("12345");

        userRepository.save(user4);

        //when

        List<User> a = userRepository.nickContainPhrase("a");
        List<User> aa = userRepository.nickContainPhrase("aa");
        List<User> aaa = userRepository.nickContainPhrase("aaa");
        List<User> aaaa = userRepository.nickContainPhrase("aaaa");

        Assert.assertEquals(3, a.size());
        Assert.assertEquals(2, aa.size());
        Assert.assertEquals(2, aaa.size());
        Assert.assertEquals(1, aaaa.size());


    }
}
