package com.wildBirds.BlueChat.domain.model;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

interface UserContainFriendRepository extends JpaRepository<UserContainFriend, Long>, UserContainFriendCustom {

    @Transactional
    void removeUserContainFriendByIdUserContainFriend(Long id);
}
