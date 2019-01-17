package com.wildBirds.BlueChat.domain.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserContainFriendRepository extends JpaRepository<UserContainFriend, Long>, UserContainFriendCustom {
}
