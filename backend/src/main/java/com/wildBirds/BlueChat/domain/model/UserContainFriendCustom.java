package com.wildBirds.BlueChat.domain.model;

import java.util.List;

interface UserContainFriendCustom {
    List<UserContainFriend> getUserFriendship(Long idUser);
    UserContainFriend saveUserContainFriends(UserContainFriend userContainFriend);
}
