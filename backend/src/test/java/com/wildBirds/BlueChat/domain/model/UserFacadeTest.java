package com.wildBirds.BlueChat.domain.model;

import com.wildBirds.BlueChat.api.rest.dto.UserDto;
import com.wildBirds.BlueChat.api.rest.dto.UserDtoPass;
import com.wildBirds.BlueChat.api.rest.dto.UserDtoShort;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import java.util.List;

public class UserFacadeTest extends ConfigurationTest{

    private Logger logger = LoggerFactory.getLogger(UserFacadeTest.class);


//    @Test
//    public void shouldGetAllUsers() {
//        //given
//
//        logger.info("Running test >> shouldGetAllUsers");
//        //given
//        User user = new User();
//        user.setNick("IgorUserFacade");
//        user.setPassword("12345");
//        User savedUser = userRepository.save(user);
//        //when
//
//        List<UserDto> users = userFacade.getUsers();
//
//        //then
//        if(users.size() >= 1){
//            Assert.assertTrue(true);
//        }else{
//            Assert.assertTrue(false);
//        }
//    }

    @Test
    public void registerNewUserAndLoginUser() {
        logger.info("Running test >> shouldGetAllUsers");
        //given
        UserDtoPass userDtoPass = new UserDtoPass("IgorRegister", "sommePassword");

        //when
        UserDto registeredUser = userFacade.registerNewUser(userDtoPass);
        UserDto loginUser = userFacade.loginUser(userDtoPass);
        //then
        Assert.assertEquals(registeredUser.getIdUser(), loginUser.getIdUser());
        Assert.assertEquals(registeredUser.getNick(), loginUser.getNick());
    }

    @Test
    @Transactional
    public void shouldGetUserByPhrase() {

        logger.info("Running test >> shouldGetUserByPhrase");
        //given
        User user = new User();
        user.setNick("rrrr");
        user.setPassword("12345");

        userRepository.save(user);
        //when

        //given
        User user1 = new User();
        user1.setNick("abrrrb");
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
        user4.setNick("bbr");
        user4.setPassword("12345");

        userRepository.save(user4);

        //when

        List<UserDtoShort> a = userFacade.nickContainPhrase("r");
        List<UserDtoShort> aa = userFacade.nickContainPhrase("rr");
        List<UserDtoShort> aaa = userFacade.nickContainPhrase("rrr");
        List<UserDtoShort> aaaa = userFacade.nickContainPhrase("rrrr");

        Assert.assertEquals(3, a.size());
        Assert.assertEquals(2, aa.size());
        Assert.assertEquals(2, aaa.size());
        Assert.assertEquals(1, aaaa.size());


    }
}