import {Injectable, OnInit} from '@angular/core';
import {UserProfileService} from './user-profile.service';
import {MessageRepositoryService} from '../repository/message/message-repository.service';
import {UserDtoShort} from '../repository/user/userDtoShort';
import {ChannelDtoShort} from '../repository/channel/channelDtoShort';
import {FriendsDto} from '../repository/friend/friendsDto';

@Injectable({
    providedIn: 'root'
})
export class ConversationService implements OnInit {



    constructor(private userProfileService: UserProfileService,
                private messsageRepository: MessageRepositoryService) {


    }

    ngOnInit(): void {}


    public startConversationWith(interlocutor: UserDtoShort | ChannelDtoShort ){

        console.log("start conversation with: ",interlocutor)
        if(interlocutor instanceof UserDtoShort){

        }else {

        }
    }
}
