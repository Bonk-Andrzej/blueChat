import {Injectable} from '@angular/core';
import {UserRepositoryService} from '../repository/user/user-repository.service';
import {LoginService} from './login.service';
import {UserDto} from '../repository/user/userDto';
import {FriendsDto} from '../repository/friend/friendsDto';
import {FriendRepositoryService} from '../repository/friend/friend-repository.service';
import {BehaviorSubject, Observable} from 'rxjs';
import {ChannelDtoShort} from '../repository/channel/channelDtoShort';
import {ChannelRepositoryService} from '../repository/channel/channel-repository.service';
import {RetrieveStateApplicationService} from './retrieve-state-application.service';
import {WSRClientService} from '../WSRClient/wsrclient.service';
import {RemoteType} from '../WSRClient/types/RemoteType';
import {ChangeService} from './change.service';

@Injectable({
    providedIn: 'root'
})
export class UserProfileService {

    public userDto: UserDto = null;
    public friends: BehaviorSubject<Array<FriendsDto>> = new BehaviorSubject([]);
    public channals: BehaviorSubject<Array<ChannelDtoShort>> = new BehaviorSubject([]);
    private wsrIsConnected : boolean;

    constructor(private userRepository: UserRepositoryService,
                private friendsRepository: FriendRepositoryService,
                private channelsRepository: ChannelRepositoryService,
                private retrieveStateApplicationService: RetrieveStateApplicationService,
                private loginService: LoginService,
                private wsrClientService:WSRClientService,
                private changeService: ChangeService) {

        this.changeService.onNewAcitveFriend.subscribe((user: UserDto) =>{

            console.log("new user",user)
            const friendsDtos = this.friends.getValue();

            friendsDtos.forEach((friend)=>{
                if(friend.friend.idUser == user.idUser){
                    friend.friend.active = true;
                    console.log("find")
                }
            });
            console.log("update")
            this.friends.next([]);
            setTimeout(()=>{
                this.friends.next(friendsDtos)
            },0)

        })

        console.log('UserProfileService -- subscribe');
        this.loginService.onLogin.subscribe((user: UserDto) => {
            this.initializeUser(user);
            this.authorizeSocketConnection();
            this.retrieveStateApplicationService.saveUserId(user);
        });
        console.log('onRetrieveApplicationState -- subscribe');
        this.retrieveStateApplicationService.onRetrieveApplicationState.subscribe((user)=>{
            this.initializeUser(user);
            this.authorizeSocketConnection();
        })

        this.wsrClientService.isConnected.subscribe((status)=>{
            this.wsrIsConnected = status;
            this.authorizeSocketConnection();
        })

    }

    private authorizeSocketConnection(){
        if(this.wsrIsConnected && (this.userDto != null)){
            this.wsrClientService.WRSClient.executeRemoteProcedure(RemoteType.AUTHSESSION,this.userDto)
        }
    }

    private initializeUser(user: UserDto) {
        this.userDto = user;
        this.fetchFriends().catch();
        this.fetchChannels().catch();
    }

    private async fetchFriends() {
        const result = await this.friendsRepository.getFriendshipsList(this.userDto.idUser);
        this.friends.next(result);
    }

    public getUser(): UserDto {
        return this.userDto;
    }

    public getFriends() {
        return this.friends.asObservable();
    }

    private async fetchChannels() {
        const result = await this.channelsRepository.getShortList();
        console.warn(result);
        this.channals.next(result);
    }

    public getChannels() {
        return this.channals.asObservable();
    }

}
