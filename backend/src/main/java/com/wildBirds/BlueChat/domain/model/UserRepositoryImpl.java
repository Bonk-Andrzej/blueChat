package com.wildBirds.BlueChat.domain.model;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

class UserRepositoryImpl implements UserRepositoryCustom{

    @PersistenceContext
    EntityManager entityManager;
    @Override
    public User saveUser(User user) {
//        User toSave = new User();
//
//        toSave.setIdUser(user.getIdUser());
//        toSave.setNick(user.getNick());
//        toSave.setNick();



        return null;
    }
}
