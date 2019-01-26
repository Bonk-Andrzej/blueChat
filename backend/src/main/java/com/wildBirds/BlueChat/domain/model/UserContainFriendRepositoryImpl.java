package com.wildBirds.BlueChat.domain.model;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.Instant;
import java.util.List;

class UserContainFriendRepositoryImpl implements UserContainFriendCustom {

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public List<UserContainFriend> getUserFriendship(Long idUser) {

        String query = "SELECT userContFrind FROM UserContainFriend userContFrind " +
                "JOIN userContFrind.user1 user1 " +
                "JOIN userContFrind.user2 user2 " +
                "WHERE user1.idUser =: idUser " +
                "OR user2.idUser =: idUser";


        return entityManager.createQuery(query)
                .setParameter("idUser", idUser)
                .getResultList();
    }

    @Override
    public UserContainFriend saveUserContainFriends(Long idUser, Long idFriend) {
        UserContainFriend toSave = new UserContainFriend();

        User user1 = entityManager.find(User.class, idUser);
        User user2 = entityManager.find(User.class, idFriend);

        toSave.setUser1(user1);
        toSave.setUser2(user2);
        toSave.setDateFriendShip(Instant.now());
        entityManager.persist(toSave);


        return toSave;
    }

//    @Override
//    public UserContainFriend createFriendship(Long idSender, Long idReceiver) {
//
//        UserContainFriend userContainFriend = new UserContainFriend();
//
//        User sender = entityManager.find(User.class, idSender);
//        User receiver = entityManager.find(User.class, idReceiver);
//
//        userContainFriend.setDateFriendShip(Instant.now());
//        userContainFriend.setUser1(sender);
//        userContainFriend.setUser2(receiver);
//
//
//        entityManager.persist(userContainFriend);
//
//        return userContainFriend;
//    }


}