import {MessageDto} from "../../repository/message/messageDto";
import {UserProfileService} from "../user-profile.service";
import {UserObs} from "./userObs";
import {ChannelMessageDto} from "../../repository/channelMessage/channelMessageDto";

export class MessageObs {


    private sandedByMe: boolean;
    private messageId: number
    private content: string;
    private readBy: Array<number>;
    private senderId: number;
    private interlocutorId: number;
    private sentDate: string;


    constructor() {
        this.readBy = [];
        this.sandedByMe = false;
    }

    public static createFromMessage(message: MessageDto, userProfileService: UserProfileService): MessageObs{
        const messageObs = new MessageObs();
        const loggedUser = userProfileService.getUser();

        messageObs.messageId = message.idMessage;
        messageObs.content = message.content;
        messageObs.senderId = message.senderId;
        messageObs.interlocutorId = message.receiverId;
        messageObs.sentDate = message.sentDate;
        messageObs.setReadBy_Message(message,messageObs,loggedUser);
        messageObs.setSandedByMe_Message(loggedUser, message, messageObs);
        return messageObs;

    }
    public static createFromChannel(message: ChannelMessageDto, userProfileService: UserProfileService): MessageObs{
        const messageObs = new MessageObs();
        const loggedUser = userProfileService.getUser();

        messageObs.messageId = message.idChannelsMessageDto;
        messageObs.content = message.content;
        messageObs.senderId = message.sender.idUser;
        messageObs.interlocutorId = message.channel.idChannel;
        messageObs.sentDate = message.sentDate;
        messageObs.setReadBy_Channel(message,messageObs,loggedUser);
        messageObs.setSandedByMe_Channel(loggedUser, message, messageObs);
        return messageObs;

    }

    // Utils methods
    private setSandedByMe_Message(loggedUser: UserObs, message: MessageDto, messageObs: MessageObs) {
        if (loggedUser.getIdUser() == message.senderId) {
            messageObs.sandedByMe = true;
        }
    }
    private setSandedByMe_Channel(loggedUser: UserObs, message: ChannelMessageDto, messageObs: MessageObs) {
        if (loggedUser.getIdUser() == message.sender.idUser) {
            messageObs.sandedByMe = true;
        }
    }
    private setReadBy_Channel(message: ChannelMessageDto, messageObs: MessageObs, loggedUser: UserObs){
        messageObs.readBy.push(...message.readBy);

    }
    private setReadBy_Message(message: MessageDto, messageObs: MessageObs, loggedUser: UserObs){

        if(message.isRead && messageObs.sandedByMe){
            messageObs.readBy.push(loggedUser.getIdUser());
        }else if(message.isRead){
            messageObs.readBy.push(messageObs.senderId)
        }
    }


}