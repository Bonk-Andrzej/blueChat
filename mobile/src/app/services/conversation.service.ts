import {Injectable} from '@angular/core';
import {UserProfileService} from './user-profile.service';
import {MessageRepositoryService} from '../repository/message/message-repository.service';
import {ChannelDtoShort} from '../repository/channel/channelDtoShort';
import {BehaviorSubject} from 'rxjs';
import {MessageDto} from '../repository/message/messageDto';
import {WSRClientService} from '../WSRClient/wsrclient.service';
import {LocalType} from '../WSRClient/types/LocalType';
import {RemoteType} from '../WSRClient/types/RemoteType';
import {UserShortObs} from "./model/userShortObs";

@Injectable({
    providedIn: 'root'
})
export class ConversationService {

    conversation = new BehaviorSubject<Array<MessageDto>>([]);
    interlocutorName = new BehaviorSubject('');
    interlocutorId: number = null;
    isChannelConversation = false;

    constructor(private userProfileService: UserProfileService,
                private messageRepository: MessageRepositoryService,
                private wsrClientService: WSRClientService
    ) {

        this.wsrClientService.WRSClient.addProcedure(LocalType.ADDMESSAGE, new MessageDto(), message => {
            if(message.receiverId == this.userProfileService.getUser().getIdUser() && message.senderId == this.interlocutorId){
            const conversation = this.conversation.getValue();
            conversation.push(message);
            this.conversation.next(conversation);
            }

        });

        this.wsrClientService.WRSClient.addProcedure(LocalType.ADDMYMESSAGE, new MessageDto(), message => {
            if(message.receiverId == this.interlocutorId && message.senderId == this.userProfileService.getUser().getIdUser() ){
                const conversation = this.conversation.getValue();
                conversation.push(message);
                this.conversation.next(conversation);
            }
        });
    }

    public async startConversationWithUser(interlocutor: UserShortObs) {

        this.isChannelConversation = false;
        const user = this.userProfileService.getUser();
        const conversation = await this.messageRepository.getConversation(user.getIdUser(), interlocutor.getIdUser(), 100, 0);
        this.conversation.next(conversation);
        this.interlocutorName.next(interlocutor.getNick());
        this.interlocutorId = interlocutor.getIdUser();
        console.log(conversation, ' <<<<<< fetched data');
    }

    public async startConversationWithChannel(interlocutor: ChannelDtoShort) {
        this.isChannelConversation = true;

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
