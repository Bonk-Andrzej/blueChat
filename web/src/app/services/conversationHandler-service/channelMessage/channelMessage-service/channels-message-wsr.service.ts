import { Injectable } from '@angular/core';
import {UserDTO} from '../../../../repository/user-repository/user-d-t.o';
import {ChannelDTO} from '../../../../repository/channel-repository/channelDTO';
import {BehaviorSubject, Observable} from 'rxjs';
import {WSRClientService} from '../../../WSRClient/wsrclient.service';
import {LocalType} from '../../../WSRClient/types/LocalType';
import {RemoteType} from '../../../WSRClient/types/RemoteType';
import {ChannelsMessageWsrDTO} from '../../../WSRClient/dto/ChannelsMessageWsrDTO';
import {ChannelsMessageRepositoryService} from '../../../../repository/channelsMessage-repository/channels-message-repository.service';
import {ChannelsMessageDTO} from '../../../../repository/channelsMessage-repository/channelsMessageDTO';

@Injectable({
  providedIn: 'root'
})
export class ChannelsMessageWsrService {

  private sender: UserDTO;
  private channel: ChannelDTO;

    private onSetChannelEvent: () => void;
    private messages: Array<ChannelsMessageDTO>;
    private messagesObs: BehaviorSubject<Array<ChannelsMessageDTO>>;

    constructor(
        private messageRepository: ChannelsMessageRepositoryService,
        private wsr: WSRClientService
    ) {

        this.messages = [];
        this.messagesObs = new BehaviorSubject<Array<ChannelsMessageDTO>>(this.messages);
        wsr.WRSClient.addProcedure(LocalType.REFRESHCHANNELMESSAGES, new ChannelsMessageWsrDTO(), data => {

                console.log("ADD NEW message: ", data);
                const messageDTO = new ChannelsMessageDTO(null, data.senderId, data.channelId, data.content, data.sentDate);
                this.messages.push(messageDTO);
                this.messagesObs.next(this.messages);


        });

        wsr.WRSClient.addProcedure(LocalType.ADDMYCHANNELMESSAGE, new ChannelsMessageWsrDTO(), data => {

            if (data.senderId == this.sender.idUser) {

                console.log("ADD MY message: ", data)
                const messageDTO = new ChannelsMessageDTO(null, data.senderId, data.channelId,data.content, data.sentDate);
                this.messages.push(messageDTO);
                this.messagesObs.next(this.messages);
            }

        });

    }

    public setChannel(channel: ChannelDTO) {
        this.channel = channel;
        this.messages = [];
        if (this.onSetChannelEvent) {
            this.onSetChannelEvent();
        }
    }

    public getChannel(): ChannelDTO {
        return this.channel;
    }

    public setSender(sender: UserDTO) {
        this.sender = sender;
        console.log(sender, 'sender <<<');
    }

    public getSender(): UserDTO {
        return this.sender;
    }


    public getConversation(limit: number, startBound: number): void {
        this.messageRepository.getConversation(this.channel, limit, startBound).subscribe(value => {
            console.log("Conversation REST >> :", value);
            this.messages = value;
            this.messagesObs.next(this.messages)
        });
    }

    public getMessagesObs(): Observable<Array<ChannelsMessageDTO>> {
        return this.messagesObs.asObservable();
    }


    public sendMessage(content: string) {
        if (this.channel) {

            const channelsMessagesWsrDto = new ChannelsMessageWsrDTO();
            channelsMessagesWsrDto.content = content;
            channelsMessagesWsrDto.channelId = this.channel.idChannel;
            channelsMessagesWsrDto.senderId = this.sender.idUser;
            this.wsr.WRSClient.executeRemoteProcedure(RemoteType.FORWARDCHANNELSMESSAGE, channelsMessagesWsrDto)
        }
    }

    public onSetOnChannel(event: () => void) {
        this.onSetChannelEvent = event;
    }
}
