package com.wildBirds.BlueChat.domain.model;

import java.util.List;

interface UserRepositoryCustom {
    User saveUser(User user);
    List<User> nickContainPhrase(String phrase);
}
