import {Injectable} from '@angular/core';
import {UserProfileService} from './user-profile.service';
import {MessageRepositoryService} from '../repository/message/message-repository.service';
import {UserDtoShort} from '../repository/user/userDtoShort';
import {ChannelDtoShort} from '../repository/channel/channelDtoShort';
import {BehaviorSubject} from 'rxjs';
import {MessageDto} from '../repository/message/messageDto';
import {WSRClientService} from '../WSRClient/wsrclient.service';
import {LocalType} from '../WSRClient/types/LocalType';
import {RemoteType} from '../WSRClient/types/RemoteType';

@Injectable({
    providedIn: 'root'
})
export class ConversationService {

    conversation = new BehaviorSubject<Array<MessageDto>>([]);
    interlocutorName = new BehaviorSubject('');
    interlocutorId: number = null;

    constructor(private userProfileService: UserProfileService,
                private messageRepository: MessageRepositoryService,
                private wsrClientService: WSRClientService
    ) {

        this.wsrClientService.WRSClient.addProcedure(LocalType.ADDMESSAGE, new MessageDto(), message => {
            const conversation = this.conversation.getValue();
            conversation.push(message);
            this.conversation.next(conversation);

        });

        this.wsrClientService.WRSClient.addProcedure(LocalType.ADDMYMESSAGE, new MessageDto(), message => {
            const conversation = this.conversation.getValue();
            conversation.push(message);
            this.conversation.next(conversation);
        });
    }

    public async startConversationWithUser(interlocutor: UserDtoShort) {

        const user = this.userProfileService.getUser();
        const conversation = await this.messageRepository.getConversation(user.idUser, interlocutor.idUser, 10, 0);
        this.conversation.next(conversation);
        this.interlocutorName.next(interlocutor.nick);
        this.interlocutorId = interlocutor.idUser;
        console.log(conversation, ' <<<<<< fetched data');
    }

    public async startConversationWithChannel(interlocutor: ChannelDtoShort) {

    }

    public getConversation() {
        return this.conversation.asObservable();
    }

    public getInterlocutorName() {
        return this.interlocutorName.asObservable();
    }

    public getInterlocutorId() {
        return this.interlocutorId;
    }

    public sendMessage(messageDto: MessageDto) {
        this.wsrClientService.WRSClient.executeRemoteProcedure(RemoteType.FORWARDMESSAGE, messageDto);
    }

}
