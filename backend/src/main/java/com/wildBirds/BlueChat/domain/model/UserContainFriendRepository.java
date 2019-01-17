package com.wildBirds.BlueChat.domain.model;

import org.springframework.data.jpa.repository.JpaRepository;

interface UserContainFriendRepository extends JpaRepository<UserContainFriend, Long>, UserContainFriendCustom {

    void deleteByIdUserContainFriend(Long id);
}
