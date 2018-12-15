package com.wildBirds.BlueChat.domain.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface UserRepository extends JpaRepository<User, Long> , UserRepositoryCustom {

    public List<User> findAll();

    public User getUserByNickAndPassword(String nick, String password);
}
