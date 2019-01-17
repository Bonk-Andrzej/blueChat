package com.wildBirds.BlueChat.domain.model;

import com.wildBirds.BlueChat.api.rest.dto.FriendsDto;
import com.wildBirds.BlueChat.api.rest.dto.UserDtoShort;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;

import javax.transaction.Transactional;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class UserContainFriendFacadeTest extends ConfigurationTest {

    Logger logger = LoggerFactory.getLogger(UserContainFriendFacadeTest.class);

    @Test
    @Transactional
    public void shouldGetLoggedUserFriends() {
        logger.info("Running test >> shouldGetLoggedUserFriends");
        //given

        //firstFriendship  --- 1
        User loggedUser = new User();
        loggedUser.setNick("loggedUserFriendFacade");
        loggedUser.setPassword("password");
        loggedUser.setPassword("somepassword");
        loggedUser = userRepository.save(loggedUser);

        User hisFriend1 = new User();
        hisFriend1.setNick("MarkIgorFriends3Facade");
        hisFriend1.setPassword("somePass");
        hisFriend1 = userRepository.save(hisFriend1);

        UserContainFriend userContainFriend = new UserContainFriend();

        userContainFriend.setUser1(loggedUser);
        userContainFriend.setUser2(hisFriend1);
        userContainFriend.setDateFriendShip(Instant.now().plus(5, ChronoUnit.MINUTES));

        userContainFriendRepository.save(userContainFriend);

        //firstFriendship  --- 2

        User hisFriend2 = new User();
        hisFriend2.setNick("MarkIgorFriends4Facade");
        hisFriend2.setPassword("somePass");
        hisFriend2 = userRepository.save(hisFriend2);

        UserContainFriend userContainFriend2 = new UserContainFriend();

        userContainFriend2.setUser1(loggedUser);
        userContainFriend2.setUser2(hisFriend2);
        userContainFriend2.setDateFriendShip(Instant.now().plus(10, ChronoUnit.MINUTES));

        userContainFriendRepository.save(userContainFriend2);

        //firstFriendship  --- 3

        User hisFriend3 = new User();
        hisFriend3.setNick("MarkIgorFriends5Facade");
        hisFriend3.setPassword("somePass");
        hisFriend3 = userRepository.save(hisFriend3);

        UserContainFriend userContainFriend3 = new UserContainFriend();

        userContainFriend3.setUser1(hisFriend3);
        userContainFriend3.setUser2(loggedUser);
        userContainFriend3.setDateFriendShip(Instant.now().plus(15, ChronoUnit.MINUTES));
        userContainFriendRepository.save(userContainFriend3);

        //firstFriendship  --- 4 --without Logged user

        User hisFriend4 = new User();
        hisFriend4.setNick("MarkIgorFriends6Facade");
        hisFriend4.setPassword("somePass");
        hisFriend4 = userRepository.save(hisFriend3);

        User anotherUSer = new User();
        anotherUSer.setNick("MarkIgorFriends7Facade");
        anotherUSer.setPassword("somePass");
        anotherUSer = userRepository.save(anotherUSer);


        UserContainFriend userContainFriend4 = new UserContainFriend();

        userContainFriend4.setUser1(anotherUSer);
        userContainFriend4.setUser2(hisFriend4);
        userContainFriend4.setDateFriendShip(Instant.now().plus(20, ChronoUnit.MINUTES));
        userContainFriendRepository.save(userContainFriend4);


        //when
//        List<UserContainFriend> userFriendship = userContainFriendRepository.getUserFriendship(loggedUser.getIdUser());
        List<FriendsDto> friendsDtoList = userContainFriendFacade.getUserContainFriend(loggedUser.getIdUser());

        //then
        Assert.assertEquals(3, friendsDtoList.size());
    }

    @Test
    @Transactional
    public void shouldAddNewFriendship() {
        logger.info("Running test >> shouldAddNewFriendship");
        //given

        //firstFriendship  --- 1
        User loggedUser = new User();
        loggedUser.setNick("loggedUserFriendFacade8");
        loggedUser.setPassword("password");
        loggedUser.setPassword("somepassword");
        loggedUser = userRepository.save(loggedUser);

        User hisFriend1 = new User();
        hisFriend1.setNick("MarkIgorFriends3Facade8");
        hisFriend1.setPassword("somePass");
        hisFriend1 = userRepository.save(hisFriend1);

        FriendsDto friendsDto = new FriendsDto();

        UserDtoShort hisFriendShort = new UserDtoShort();
        hisFriendShort.setIdUser(hisFriend1.getIdUser());

        friendsDto.setHisFriend(hisFriendShort);
        friendsDto.setDateFriendShip(Instant.now().plus(5, ChronoUnit.MINUTES));

        //when
        FriendsDto savedFriends = userContainFriendFacade.addFriendship(loggedUser.getIdUser(), friendsDto);


        //then

        Assert.assertNotNull(savedFriends.getIdUserContainFriend());
        Assert.assertEquals("MarkIgorFriends3Facade8",savedFriends.getHisFriend().getName());
    }

    @Test
    @Transactional
    public void shouldRemoveRelation() {
        logger.info("Running test >> shouldRemoveRelation");
        //given

        //firstFriendship  --- 1
        User loggedUser = new User();
        loggedUser.setNick("loggedUserFriendFacade9");
        loggedUser.setPassword("password");
        loggedUser.setPassword("somepassword");
        loggedUser = userRepository.save(loggedUser);

        User hisFriend1 = new User();
        hisFriend1.setNick("MarkIgorFriends3Facade9");
        hisFriend1.setPassword("somePass");
        hisFriend1 = userRepository.save(hisFriend1);

        FriendsDto friendsDto = new FriendsDto();

        UserDtoShort hisFriendShort = new UserDtoShort();
        hisFriendShort.setIdUser(hisFriend1.getIdUser());

        friendsDto.setHisFriend(hisFriendShort);
        friendsDto.setDateFriendShip(Instant.now().plus(5, ChronoUnit.MINUTES));

        FriendsDto savedFriends = userContainFriendFacade.addFriendship(loggedUser.getIdUser(), friendsDto);
        //when
        userContainFriendFacade.remove(savedFriends);

        //then

        try {
            userContainFriendRepository.getOne(savedFriends.getIdUserContainFriend());
            Assert.assertTrue(false);
        } catch (JpaObjectRetrievalFailureException e) {
            Assert.assertTrue(true);
        }
    }
}