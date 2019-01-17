package com.wildBirds.BlueChat.domain.model;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
}
