import {Injectable} from '@angular/core';
import {UserProfileService} from './user-profile.service';
import {MessageRepositoryService} from '../repository/message/message-repository.service';
import {ChannelDtoShort} from '../repository/channel/channelDtoShort';
import {BehaviorSubject} from 'rxjs';
import {MessageDto} from '../repository/message/messageDto';
import {WSRClientService} from '../WSRClient/wsrclient.service';
import {LocalType} from '../WSRClient/types/LocalType';
import {RemoteType} from '../WSRClient/types/RemoteType';
import {UserShortObs} from './model/userShortObs';
import {MessageObs} from './model/messageObs';
import {ChannelMessageRepositoryService} from '../repository/channelMessage/channel-message-repository.service';
import {ChannelMessageDto} from "../repository/channelMessage/channelMessageDto";

@Injectable({
    providedIn: 'root'
})
export class ConversationService {

    isChannelConversation = false;
    private userInterlocutor: UserShortObs;
    private channelInterlocutor: ChannelDtoShort;

    conversationHeader = new BehaviorSubject('');
    conversation = new BehaviorSubject<Array<MessageObs>>([]);

    constructor(private userProfileService: UserProfileService,
                private messageRepository: MessageRepositoryService,
                private wsrClientService: WSRClientService,
                private channelMessageRepositoryService: ChannelMessageRepositoryService
    ) {

        this.wsrClientService.WRSClient.addProcedure(LocalType.ADDMESSAGE, new MessageDto(), message => {
            if (message.receiver.idUser == this.userProfileService.getUser().getIdUser() && message.sender.idUser == this.userInterlocutor.getIdUser()) {
                const conversation = this.conversation.getValue();
                conversation.push(MessageObs.createFromMessage(message, this.userProfileService));
                this.conversation.next(conversation);
            }
        });
        this.wsrClientService.WRSClient.addProcedure(LocalType.ADDMYMESSAGE, new MessageDto(), message => {
            if (message.receiver.idUser == this.userInterlocutor.getIdUser() && message.sender.idUser == this.userProfileService.getUser().getIdUser()) {
                const conversation = this.conversation.getValue();
                conversation.push(MessageObs.createFromMessage(message, this.userProfileService));
                this.conversation.next(conversation);
            }
        });
        this.wsrClientService.WRSClient.addProcedure(LocalType.ADDMYCHANNELMESSAGE, new ChannelMessageDto(),channelMessage => {
            if(channelMessage.channel.idChannel == this.channelInterlocutor.idChannel){
                const conversation = this.conversation.getValue();
                conversation.push(MessageObs.createFromChannel(channelMessage, this.userProfileService));
                this.conversation.next(conversation);
            }
        });
        this.wsrClientService.WRSClient.addProcedure(LocalType.ADDCHANNELMESSAGE, new ChannelMessageDto(),channelMessage => {
            if(channelMessage.channel.idChannel == this.channelInterlocutor.idChannel){
                const conversation = this.conversation.getValue();
                conversation.push(MessageObs.createFromChannel(channelMessage, this.userProfileService));
                this.conversation.next(conversation);
            }
        })
    }

    public async startConversationWithUser(interlocutor: UserShortObs) {

        this.isChannelConversation = false;
        this.userInterlocutor = interlocutor;
        const user = this.userProfileService.getUser();

        const newConversation = [];
        const conversation = await this.messageRepository.getConversation(user.getIdUser(), interlocutor.getIdUser(), 100, 0);
        for (let messageDto of conversation) {
            newConversation.push(MessageObs.createFromMessage(messageDto, this.userProfileService))
        }
        this.conversation.next(newConversation);
        this.conversationHeader.next(interlocutor.getNick());
    }

    public async startConversationWithChannel(interlocutor: ChannelDtoShort) {
        this.isChannelConversation = true;
        this.channelInterlocutor = interlocutor;

        const newConversation = [];
        const conversation = await this.channelMessageRepositoryService.getConversation(interlocutor.idChannel, 100, 0);
        for (let ChannelMessageDto of conversation) {
            newConversation.push(MessageObs.createFromChannel(ChannelMessageDto, this.userProfileService))
        }
        this.conversation.next(newConversation);
        this.conversationHeader.next(interlocutor.name);

    }

    public getConversationObs() {
        return this.conversation.asObservable();
    }

    public getConversationHeaderObs() {
        return this.conversationHeader.asObservable();
    }

    public sendMessage(messageContent: string) {

        if(this.isChannelConversation){
            let channelMessageDto = new ChannelMessageDto();
            channelMessageDto.content = messageContent;
            channelMessageDto.sender = this.userProfileService.getUser().toUserDtoShort();
            channelMessageDto.channel = this.getChanelInterlocutor();
            this.wsrClientService.WRSClient.executeRemoteProcedure(RemoteType.FORWARDCHANNELMESSAGE, channelMessageDto);
        }else {
            let messageDto = new MessageDto();
            messageDto.content = messageContent;
            messageDto.sender = this.userProfileService.getUser().toUserDtoShort();
            messageDto.receiver = this.getUserInterlocutor().toUserDtoShort();
            this.wsrClientService.WRSClient.executeRemoteProcedure(RemoteType.FORWARDMESSAGE, messageDto);
        }
    }

    public getUserInterlocutor(): UserShortObs {
        return this.userInterlocutor;
    }

    public getChanelInterlocutor(): ChannelDtoShort{
        return this.channelInterlocutor;
    }

}
