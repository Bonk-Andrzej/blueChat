package com.wildBirds.BlueChat.domain.model;


import com.wildBirds.BlueChat.api.rest.dto.FriendsDto;
import com.wildBirds.BlueChat.api.rest.dto.UserDtoShort;

class UserContainFriendService {

    private UserContainFriendRepository repository;
    private UserRepository userRepository;


    public UserContainFriend toEntity(Long idLoggedUser, FriendsDto friendsDto) {
        UserContainFriend userContainFriend = new UserContainFriend();

        if (friendsDto.getIdUserContainFriend() != null) {
            userContainFriend.setIdUserContainFriend(friendsDto.getIdUserContainFriend());
        }

        User loggedUserEntity = new User();
        loggedUserEntity.setIdUser(idLoggedUser);

        User hisFriendEntity = new User();
        hisFriendEntity.setIdUser(friendsDto.getHisFriend().getIdUser());

        userContainFriend.setUser1(loggedUserEntity);
        userContainFriend.setUser2(hisFriendEntity);
        userContainFriend.setDateFriendShip(friendsDto.getDateFriendShip());

        return userContainFriend;
    }


    public FriendsDto toDto(Long idUser, UserContainFriend userContainFriend) {
        FriendsDto friendsDto = new FriendsDto();

        setId(userContainFriend, friendsDto);

        friendsDto.setDateFriendShip(userContainFriend.getDateFriendShip());

        UserDtoShort friend = new UserDtoShort();

        checkWhoIsFriend(idUser, userContainFriend, friend);

        friendsDto.setHisFriend(friend);

        return friendsDto;
    }

    private void checkWhoIsFriend(Long idUser, UserContainFriend userContainFriend, UserDtoShort friend) {
        if(!userContainFriend.getUser1().getIdUser().equals(idUser)){

            friend.setIdUser(userContainFriend.getUser1().getIdUser());
        }else {
            friend.setIdUser(userContainFriend.getUser2().getIdUser());
        }
    }

    private void setId(UserContainFriend userContainFriend, FriendsDto friendsDto) {
        if (userContainFriend.getIdUserContainFriend() != null) {
            friendsDto.setIdUserContainFriend(userContainFriend.getIdUserContainFriend());
        }
    }


}
