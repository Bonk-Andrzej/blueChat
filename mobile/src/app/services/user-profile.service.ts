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
import {FriendsObs} from "./model/friendsObs";
import {UserObs} from "./model/userObs";
import {UserDtoWithMessage} from '../repository/user/userDtoWithMessage';

@Injectable({
    providedIn: 'root'
})
export class UserProfileService {

    public userBeh: BehaviorSubject<UserObs> = new BehaviorSubject(null);
    public friends: BehaviorSubject<Array<FriendsObs>> = new BehaviorSubject([]);
    public channels: BehaviorSubject<Array<ChannelDtoShort>> = new BehaviorSubject([]);
    public usersWithNewMessage: BehaviorSubject<Array<UserDtoWithMessage>> = new BehaviorSubject([]);
    private wsrIsConnected: boolean;

    constructor(private userRepository: UserRepositoryService,
                private friendsRepository: FriendRepositoryService,
                private channelsRepository: ChannelRepositoryService,
                private retrieveStateApplicationService: RetrieveStateApplicationService,
                private loginService: LoginService,
                private wsrClientService: WSRClientService,
                private changeService: ChangeService) {

        this.changeService.onFriendJoin.subscribe((user: UserDto) => {

            console.log("new user", user)
            const friendsDtos = this.friends.getValue();

            friendsDtos.forEach((friend) => {
                if (friend.getFriend().getIdUser() == user.idUser) {
                    friend.getFriend().setActive(true);
                }
            });
        });
        this.changeService.onFriendLeave.subscribe((user: UserDto) => {

            console.log("new user", user)
            const friendsDtos = this.friends.getValue();

            friendsDtos.forEach((friend) => {
                if (friend.getFriend().getIdUser() == user.idUser) {
                    friend.getFriend().setActive(false);
                }
            });
        });
        this.loginService.onLogin.subscribe((user: UserDto) => {
            this.initializeUser(user);
            this.authorizeSocketConnection();
            this.retrieveStateApplicationService.saveUserId(user);
        });
        this.retrieveStateApplicationService.onRetrieveApplicationState.subscribe((user) => {
            this.initializeUser(user);
            this.authorizeSocketConnection();
        })
        this.retrieveStateApplicationService.onRemoveUserId.subscribe(() => {
            this.eraseData()
        })
        this.wsrClientService.isConnected.subscribe((status) => {
            this.wsrIsConnected = status;
            this.authorizeSocketConnection();
        })

    }

    private authorizeSocketConnection() {
        if (this.wsrIsConnected && (this.userBeh.getValue() != null)) {
            this.wsrClientService.WRSClient.executeRemoteProcedure(RemoteType.AUTHSESSION, this.userBeh.getValue().toUserDto())
        }
    }
    private initializeUser(user: UserDto) {
        this.userBeh.next(UserObs.create(user))
        this.fetchFriends().catch();
        this.fetchChannels().catch();
        this.fetchUsersWithMsg().catch();
    }
    private async fetchFriends() {
        const result = await this.friendsRepository.getFriendshipsList(this.userBeh.getValue().getIdUser());
        const friends = [];
        for (let friendsDto of result) {
            friends.push(FriendsObs.create(friendsDto));
        }
        this.friends.next(friends);
    }
    public getUser(): UserObs {
        return this.userBeh.getValue()
    }
    public getUserObs(): Observable<UserObs> {
        return this.userBeh.asObservable()
    }
    public getFriends(): Observable<Array<FriendsObs>> {
        return this.friends.asObservable();
    }
    private async fetchChannels() {
        const result = await this.channelsRepository.getShortList(this.userBeh.getValue().getIdUser());
        console.warn(result);
        this.channels.next(result);
    }
    public getChannels(): Observable<Array<ChannelDtoShort>> {
        return this.channels.asObservable();
    }
    public eraseData() {
        this.userBeh = new BehaviorSubject<UserObs>(null);
        this.friends.next([]);
        this.channels.next([]);
        this.wsrIsConnected = false;
    }

    private async fetchUsersWithMsg(){
        const result = await this.userRepository.gerUserWithMessage(this.userBeh.getValue().getIdUser());
        this.usersWithNewMessage.next(result)
    }

    public getUsersWuthMsg(){
        return this.usersWithNewMessage.asObservable()
    }


}
