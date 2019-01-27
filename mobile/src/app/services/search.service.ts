import {Injectable} from '@angular/core';
import {UserRepositoryService} from '../repository/user/user-repository.service';
import {ChannelRepositoryService} from '../repository/channel/channel-repository.service';
import {BehaviorSubject, Observable} from 'rxjs';
import {ChannelDtoShort} from '../repository/channel/channelDtoShort';
import {UserDtoShort} from '../repository/user/userDtoShort';

@Injectable({
    providedIn: 'root'
})
export class SearchService {

    private users: BehaviorSubject<Array<UserDtoShort>> = new BehaviorSubject([]);
    private channels: BehaviorSubject<Array<ChannelDtoShort>> = new BehaviorSubject([]);

    constructor(private userRepository: UserRepositoryService,
                private channelRepository: ChannelRepositoryService) {


    }

    public search(phrase: string) {
        this.fetchUsers(phrase);
    }

    public getUsers() : Observable<Array<UserDtoShort>> {
        return this.users.asObservable();
    }
    public getchannels() : Observable<Array<ChannelDtoShort>> {
        return this.channels.asObservable();
    }


    private async fetchUsers(userName: string) {
        const result = await this.userRepository.findUserByPhrase(userName);
        const users = [];
        for (let user in result) {
            users.push(user);
        }
        this.users.next(users);
    }

    // private async fetchChannels(channelName: string) {
    //     const result = await this.channelRepository.findUserByPhrase(channelName);
    //     const channels = [];
    //     for (let channel in result) {
    //         channels.push(channel)
    //     }
    //     this.users.next(channels);
    // }

}
