package com.wildBirds.BlueChat.domain.model;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

class UserRepositoryImpl implements UserRepositoryCustom{

    @PersistenceContext
    EntityManager entityManager;
    @Override
    @Transactional
    public User saveUser(User user) {
        return entityManager.merge(user);
    }

    @Override
    public List<User> nickContainPhrase(String phrase) {

        String query = "SELECT user FROM User user " +
                "WHERE user.nick LIKE :phrase";

        return entityManager.createQuery(query)
                .setParameter("phrase", "%"+phrase+"%")
                .setMaxResults(10)
                .getResultList();

    }
}
