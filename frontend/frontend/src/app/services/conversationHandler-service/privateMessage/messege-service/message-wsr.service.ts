import {Injectable} from '@angular/core';

import {MessagesRepositoryService} from '../../repository/message-repostiory/message-repository.service';
import {UserDTO} from '../../repository/user-repository/user-d-t.o';
import {MessageDTO} from '../../repository/message-repostiory/messageDTO';
import {WSRClientService} from "../WSRClient/wsrclient.service";
import {LocalType} from "../WSRClient/types/LocalType";
import {MessageDTO as MessageDTOWSR} from "../WSRClient/dto/MessageDTO";
import {RemoteType} from "../WSRClient/types/RemoteType";
import {BehaviorSubject, Observable} from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class MessageWsrService {

    private sender: UserDTO;
    private receiver: UserDTO;
    private onSetReceiverEvent: () => void;
    private messages: Array<MessageDTO>;
    private messagesObs: BehaviorSubject<Array<MessageDTO>>;

    constructor(
        private messageRepository: MessagesRepositoryService,
        private wsr: WSRClientService
    ) {

        this.messages = [];
        this.messagesObs = new BehaviorSubject<Array<MessageDTO>>(this.messages);
        wsr.WRSClient.addProcedure(LocalType.ADDMESSAGE, new MessageDTOWSR(), data => {

            if (data.getReceiverId() == this.sender.idUser && data.getSenderId() == this.receiver.idUser) {

                console.log("ADD NEW message: ", data)
                const messageDTO = new MessageDTO(null, data.getContent(), data.getSentData(), data.getSenderId(), data.getReceiverId());
                this.messages.push(messageDTO);
                this.messagesObs.next(this.messages);
            }

        });

        wsr.WRSClient.addProcedure(LocalType.ADDMYMESSAGE, new MessageDTOWSR(), data => {

            if (data.getSenderId() == this.sender.idUser) {

                console.log("ADD MY message: ", data)
                const messageDTO = new MessageDTO(null, data.getContent(), data.getSentData(), data.getSenderId(), data.getReceiverId());
                this.messages.push(messageDTO);
                this.messagesObs.next(this.messages);
            }

        });

    }

    public setReceiver(receiver: UserDTO) {
        this.receiver = receiver;
        this.messages = [];
        if (this.onSetReceiverEvent) {
            this.onSetReceiverEvent();
        }
    }

    public getReceiver(): UserDTO {
        return this.receiver;
    }

    public setSender(sender: UserDTO) {
        this.sender = sender;
        console.log(sender, 'sender <<<');
    }

    public getSender(): UserDTO {
        return this.sender;
    }


    public getConversation(limit: number, startBound: number): void {
        this.messageRepository.getConversation(this.sender, this.receiver, limit, startBound).subscribe(value => {
            console.log("Conversation REST >> :", value)
            this.messages = value;
            this.messagesObs.next(this.messages)
        });
    }

    public getMessagesObs(): Observable<Array<MessageDTO>> {
        return this.messagesObs.asObservable();
    }


    public sendMessage(content: string) {
        if (this.receiver) {

            const messageDTOWSR = new MessageDTOWSR();
            messageDTOWSR.setContent(content);
            messageDTOWSR.setReceiverId(this.receiver.idUser);
            messageDTOWSR.setSenderId(this.sender.idUser);
            this.wsr.WRSClient.executeRemoteProcedure(RemoteType.FORWARDMESSAGE, messageDTOWSR)
        }
    }

    public onSetReceiver(event: () => void) {
        this.onSetReceiverEvent = event;
    }
}
