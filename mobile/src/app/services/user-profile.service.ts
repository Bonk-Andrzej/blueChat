import {Injectable} from '@angular/core';
import {UserRepositoryService} from '../repository/user/user-repository.service';
import {LoginService} from './login.service';
import {UserDto} from '../repository/user/userDto';
import {FriendRepositoryService} from '../repository/friend/friend-repository.service';
import {BehaviorSubject, Observable} from 'rxjs';
import {ChannelDtoShort} from '../repository/channel/channelDtoShort';
import {ChannelRepositoryService} from '../repository/channel/channel-repository.service';
import {RetrieveStateApplicationService} from './retrieve-state-application.service';
import {WSRClientService} from '../WSRClient/wsrclient.service';
import {RemoteType} from '../WSRClient/types/RemoteType';
import {ChangeService} from './change.service';
import {FriendsObs} from './model/friendsObs';
import {UserObs} from './model/userObs';
import {UserDtoWithMessage} from '../repository/user/userDtoWithMessage';
import {FriendsDto} from '../repository/friend/friendsDto';
import {UserShortObs} from './model/userShortObs';

@Injectable({
    providedIn: 'root'
})
export class UserProfileService {

    private readonly userBeh: BehaviorSubject<UserObs> = new BehaviorSubject(null);
    private readonly friends: BehaviorSubject<Array<FriendsObs>> = new BehaviorSubject([]);
    private readonly channels: BehaviorSubject<Array<ChannelDtoShort>> = new BehaviorSubject([]);
    private readonly usersWithNewMessage: BehaviorSubject<Array<UserDtoWithMessage>> = new BehaviorSubject([]);
    private wsrIsConnected: boolean;

    constructor(private userRepository: UserRepositoryService,
                private friendsRepository: FriendRepositoryService,
                private channelsRepository: ChannelRepositoryService,
                private retrieveStateApplicationService: RetrieveStateApplicationService,
                private loginService: LoginService,
                private wsrClientService: WSRClientService,
                private changeService: ChangeService) {

        this.changeService.onFriendJoin.subscribe((user: UserDto) => {

            console.log('new user', user);
            const friendsDtos = this.friends.getValue();

            friendsDtos.forEach((friend) => {
                if (friend.getFriend().getIdUser() == user.idUser) {
                    friend.getFriend().setActive(true);
                }
            });
        });
        this.changeService.onFriendLeave.subscribe((user: UserDto) => {

            console.log('new user', user);
            const friendsDtos = this.friends.getValue();

            friendsDtos.forEach((friend) => {
                if (friend.getFriend().getIdUser() == user.idUser) {
                    friend.getFriend().setActive(false);
                }
            });
        });
        this.retrieveStateApplicationService.onRemoveUserId.subscribe(() => {
            this.eraseData();
        });

        this.loginService.onLogin.subscribe((user: UserDto) => {
            this.initUser(user);
        });
        this.retrieveStateApplicationService.onRetrieveApplicationState.subscribe((user) => {
            this.initUser(user);
        });

        this.wsrClientService.isConnected.subscribe((status) => {
            this.wsrIsConnected = status;
            this.authorizeSocketConnection();
        });


    }

    private initUser(user: UserDto) {
        this.userBeh.next(UserObs.create(user));
        this.retrieveStateApplicationService.saveUserId(user);

        this.authorizeSocketConnection();
        this.fetchFriends().catch();
        this.fetchChannels().catch();
        this.fetchUsersWithMsg().catch();
    }

    private authorizeSocketConnection() {
        if (this.wsrIsConnected && (this.userBeh.getValue() != null)) {
            this.wsrClientService.WRSClient.executeRemoteProcedure(RemoteType.AUTHSESSION, this.userBeh.getValue().toUserDto());
        }
    }

    private async fetchFriends() {
        const result = await this.friendsRepository.getFriendshipsList(this.userBeh.getValue().getIdUser());

        console.log('POBRANE DANE >>>>>>>>>>', result);
        const friends = [];
        console.log(result);
        for (let friendsDto of result) {
            friends.push(FriendsObs.create(friendsDto));
        }

        console.log(friends);
        this.friends.next(friends);
    }

    private async fetchChannels() {
        const result = await this.channelsRepository.getShortList(this.userBeh.getValue().getIdUser());
        console.warn(result);
        this.channels.next(result);
    }

    private async fetchUsersWithMsg() {
        const result = await this.userRepository.gerUserWithMessage(this.userBeh.getValue().getIdUser());
        this.usersWithNewMessage.next(result);
    }

    public getIdFriendship(idFriend: number): number {
        for (let friendsObs of this.friends.getValue()) {
            if (friendsObs.getFriend().getIdUser() == idFriend) {
                console.log('FOUND FRIENDSHIP IN LIST', friendsObs.getIdFrendship());
                return friendsObs.getIdFrendship();
            }
        }
    }

    public getUser(): UserObs {
        return this.userBeh.getValue();
    }

    public removeFriendFromList(idUser: number) {
        let friends = this.friends.getValue().filter(user => !(user.getFriend().getIdUser() === idUser));
        this.friends.next(friends);
    }

    public getUserObs(): Observable<UserObs> {
        return this.userBeh.asObservable();
    }

    public getFriends(): Observable<Array<FriendsObs>> {
        return this.friends.asObservable();
    }

    public getChannels(): Observable<Array<ChannelDtoShort>> {
        return this.channels.asObservable();
    }

    public eraseData() {
        this.userBeh.next(null);
        this.friends.next([]);
        this.channels.next([]);
        this.wsrIsConnected = false;
    }

    public getUsersWuthMsg() {
        return this.usersWithNewMessage.asObservable();
    }

    public removeUserWithMsg(userDtoWithMsg: UserDtoWithMessage) {

        let userDtoWithMessages = this.usersWithNewMessage.getValue().filter(user => !(user.idUser === userDtoWithMsg.idUser));

        this.usersWithNewMessage.next(userDtoWithMessages);
        this.friends.getValue().find(friend => (friend.getFriend().getIdUser() == userDtoWithMsg.idUser))
            .setNoReadMessage(0);
    }

    public addFrendDtoToFriend(friendsDto: FriendsDto) {
        let friendsList = this.friends.getValue();
        friendsList.push(FriendsObs.create(friendsDto));
        this.friends.next(friendsList);
    }

    public findFriend(friendId): UserShortObs {
        let result = this.friends.getValue().find((user) => (user.getFriend().getIdUser() == friendId));
        return (result) ? result.getFriend() : null;
    }

    public editUser(editedUser: UserDto) {
        let oldUser = this.getUser();

        editedUser.idUser = oldUser.getIdUser();

        if (editedUser.nick == '') {
            editedUser.nick = oldUser.getNick();
        }
        if (editedUser.description == '') {
            editedUser.description = oldUser.getDescription();
        }
        if (editedUser.email == '') {
            editedUser.email = oldUser.getEmail();
        }
        if (editedUser.photoDto == null) {
            editedUser.photoDto = oldUser.getPhoto();
        }
        let userObs = UserObs.create(editedUser);
        this.userBeh.next(userObs);
        this.userRepository.updateUser(editedUser);
    }
}
