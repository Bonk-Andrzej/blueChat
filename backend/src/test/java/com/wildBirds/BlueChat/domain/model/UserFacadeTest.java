package com.wildBirds.BlueChat.domain.model;

import com.wildBirds.BlueChat.api.rest.dto.UserDto;
import com.wildBirds.BlueChat.api.rest.dto.UserDtoPass;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UserFacadeTest extends ConfigurationTest{

    private Logger logger = LoggerFactory.getLogger(UserFacadeTest.class);


    @Test
    public void shouldGetAllUsers() {
        //given

        logger.info("Running test >> shouldGetAllUsers");
        //given
        User user = new User();
        user.setNick("IgorUserFacade");
        user.setPassword("12345");
        User savedUser = userRepository.save(user);
        //when

        List<UserDto> users = userFacade.getUsers();

        //then
        if(users.size() >= 1){
            Assert.assertTrue(true);
        }else{
            Assert.assertTrue(false);
        }
    }

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
}