package com.wildBirds.BlueChat.domain.model;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;

import javax.transaction.Transactional;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class UserContainFriendRepositoryTest extends ConfigurationTest {

    private Logger logger = LoggerFactory.getLogger(UserContainFriendRepositoryTest.class);


    @Test
    @Transactional
    public void shouldAddNewFriendship() {

        logger.info("Running test >> shouldAddNewFriendship");
        //given
        User loggedUser = new User();
        loggedUser.setNick("IgorFriends");
        loggedUser.setPassword("password");
        loggedUser.setPassword("somepassword");
        loggedUser = userRepository.save(loggedUser);

        User hisFriend = new User();
        hisFriend.setNick("MarkIgorFriends");
        hisFriend.setPassword("somePass");
        hisFriend = userRepository.save(hisFriend);

        //when
        UserContainFriend userContainFriend = new UserContainFriend();

        userContainFriend.setUser1(loggedUser);
        userContainFriend.setUser2(hisFriend);
        userContainFriend.setDateFriendShip(Instant.now());

        UserContainFriend savedFriendShip = userContainFriendRepository.save(userContainFriend);

        //then

        Assert.assertNotNull(savedFriendShip.getIdUserContainFriend());
        Assert.assertEquals(loggedUser.getNick(), savedFriendShip.getUser1().getNick());
        Assert.assertEquals(hisFriend.getNick(), savedFriendShip.getUser2().getNick());



    }

    @Test
    @Transactional
    public void shouldRemoveExistingFriendship() {

        logger.info("Running test >> shouldAddNewFriendship");
        //given
        User loggedUser = new User();
        loggedUser.setNick("IgorFriends2");
        loggedUser.setPassword("password");
        loggedUser.setPassword("somepassword");
        loggedUser = userRepository.save(loggedUser);

        User hisFriend = new User();
        hisFriend.setNick("MarkIgorFriends2");
        hisFriend.setPassword("somePass");
        hisFriend = userRepository.save(hisFriend);

        UserContainFriend userContainFriend = new UserContainFriend();

        userContainFriend.setUser1(loggedUser);
        userContainFriend.setUser2(hisFriend);
        userContainFriend.setDateFriendShip(Instant.now());

        UserContainFriend savedFriendShip = userContainFriendRepository.save(userContainFriend);
        //when
        userContainFriendRepository.delete(savedFriendShip);

        //then

        try {
            userContainFriendRepository.getOne(savedFriendShip.getIdUserContainFriend());
            Assert.assertTrue(false);
        } catch (JpaObjectRetrievalFailureException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    @Transactional
    public void shouldGetUserContainFriendList() {
        logger.info("Running test >> shouldAddNewFriendship");
        //given

        //firstFriendship  --- 1
        User loggedUser = new User();
        loggedUser.setNick("loggedUserFriend");
        loggedUser.setPassword("password");
        loggedUser.setPassword("somepassword");
        loggedUser = userRepository.save(loggedUser);

        User hisFriend1 = new User();
        hisFriend1.setNick("MarkIgorFriends3");
        hisFriend1.setPassword("somePass");
        hisFriend1 = userRepository.save(hisFriend1);

        UserContainFriend userContainFriend = new UserContainFriend();

        userContainFriend.setUser1(loggedUser);
        userContainFriend.setUser2(hisFriend1);
        userContainFriend.setDateFriendShip(Instant.now().plus(5, ChronoUnit.MINUTES));

        userContainFriendRepository.save(userContainFriend);

        //firstFriendship  --- 2

        User hisFriend2 = new User();
        hisFriend2.setNick("MarkIgorFriends4");
        hisFriend2.setPassword("somePass");
        hisFriend2 = userRepository.save(hisFriend2);

        UserContainFriend userContainFriend2 = new UserContainFriend();

        userContainFriend2.setUser1(loggedUser);
        userContainFriend2.setUser2(hisFriend2);
        userContainFriend2.setDateFriendShip(Instant.now().plus(10, ChronoUnit.MINUTES));

        userContainFriendRepository.save(userContainFriend2);

        //firstFriendship  --- 3

        User hisFriend3 = new User();
        hisFriend3.setNick("MarkIgorFriends5");
        hisFriend3.setPassword("somePass");
        hisFriend3 = userRepository.save(hisFriend3);

        UserContainFriend userContainFriend3 = new UserContainFriend();

        userContainFriend3.setUser1(hisFriend3);
        userContainFriend3.setUser2(loggedUser);
        userContainFriend3.setDateFriendShip(Instant.now().plus(15, ChronoUnit.MINUTES));
        userContainFriendRepository.save(userContainFriend3);

        //firstFriendship  --- 4 --without Logged user

        User hisFriend4 = new User();
        hisFriend4.setNick("MarkIgorFriends6");
        hisFriend4.setPassword("somePass");
        hisFriend4 = userRepository.save(hisFriend3);

        User anotherUSer = new User();
        anotherUSer.setNick("MarkIgorFriends7");
        anotherUSer.setPassword("somePass");
        anotherUSer = userRepository.save(anotherUSer);


        UserContainFriend userContainFriend4 = new UserContainFriend();

        userContainFriend4.setUser1(anotherUSer);
        userContainFriend4.setUser2(hisFriend4);
        userContainFriend4.setDateFriendShip(Instant.now().plus(20, ChronoUnit.MINUTES));
        userContainFriendRepository.save(userContainFriend4);


        //when
        List<UserContainFriend> userFriendship = userContainFriendRepository.getUserFriendship(loggedUser.getIdUser());

        //then
        Assert.assertEquals(3, userFriendship.size());


    }

}