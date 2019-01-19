import {Injectable} from '@angular/core';
import {UserRepositoryService} from '../repository/user/user-repository.service';
import {LoginService} from './login.service';
import {UserDto} from '../repository/user/userDto';
import {FriendsDto} from '../repository/friend/friendsDto';
import {FriendRepositoryService} from '../repository/friend/friend-repository.service';
import {BehaviorSubject} from 'rxjs';
import {ChannelDtoShort} from '../repository/channel/channelDtoShort';
import {ChannelRepositoryService} from '../repository/channel/channel-repository.service';

@Injectable({
    providedIn: 'root'
})
export class UserProfileService {

    public userDto: UserDto;
    public friends: BehaviorSubject<Array<FriendsDto>> = new BehaviorSubject([]);
    public channals: BehaviorSubject<Array<ChannelDtoShort>> = new BehaviorSubject([]);

    constructor(private userRepository: UserRepositoryService,
                private friendsRepository: FriendRepositoryService,
                private channelsRespository: ChannelRepositoryService,
                private loginService: LoginService) {

        this.loginService.onLogin.subscribe((user) => {
            this.userDto = user;
            this.fetchFriends().catch();
            this.fetchChannels().catch();
        });
        console.log('UserProfileService -- subscribe');
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
        const result = await this.channelsRespository.getShortList();
        console.warn(result)
        this.channals.next(result);
    }

    public getChannels() {
        return this.channals.asObservable();
    }

}
