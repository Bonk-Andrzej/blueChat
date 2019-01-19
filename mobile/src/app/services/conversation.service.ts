import {Injectable, OnInit} from '@angular/core';
import {UserProfileService} from './user-profile.service';
import {MessageRepositoryService} from '../repository/message/message-repository.service';
import {UserDtoShort} from '../repository/user/userDtoShort';
import {ChannelDtoShort} from '../repository/channel/channelDtoShort';
import {BehaviorSubject} from 'rxjs';
import {MessageDto} from '../repository/message/messageDto';

@Injectable({
    providedIn: 'root'
})
export class ConversationService implements OnInit {

    conversation  = new BehaviorSubject<Array<MessageDto>>([])
    interlocutorName = new BehaviorSubject("");
    constructor(private userProfileService: UserProfileService,
                private messageRepository: MessageRepositoryService) {

            }

    ngOnInit(): void {
    }


    public async startConversationWithUser(interlocutor: UserDtoShort) {

            const user = this.userProfileService.getUser();
            const conversation = await this.messageRepository.getConversation(user.idUser, interlocutor.idUser, 10, 0);
            this.conversation.next(conversation);
            this.interlocutorName.next(interlocutor.nick);
            console.log(conversation, " <<<<<< fetched data")
    }

    public async startConversationWithChannel(interlocutor: ChannelDtoShort) {

    }

    public getConversation(){
        return this.conversation.asObservable();
    }

    public getInterlocutorName(){
        return this.interlocutorName.asObservable();
    }

}
