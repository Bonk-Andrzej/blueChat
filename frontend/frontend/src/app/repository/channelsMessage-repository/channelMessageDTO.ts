export class ChannelMessageDTO {
    private _idMessageGroup: number;
    private _senderId: number;
    private _channelId: number;
    private _content: string;
    private _sentDate: string;


    constructor(idMessageGroup: number, senderId: number, channelId: number, content: string, sentDate: string) {
        this._idMessageGroup = idMessageGroup;
        this._senderId = senderId;
        this._channelId = channelId;
        this._content = content;
        this._sentDate = sentDate;
    }


    get idMessageGroup(): number {
        return this._idMessageGroup;
    }

    set idMessageGroup(value: number) {
        this._idMessageGroup = value;
    }

    get senderId(): number {
        return this._senderId;
    }

    set senderId(value: number) {
        this._senderId = value;
    }

    get channelId(): number {
        return this._channelId;
    }

    set channelId(value: number) {
        this._channelId = value;
    }

    get content(): string {
        return this._content;
    }

    set content(value: string) {
        this._content = value;
    }

    get sentDate(): string {
        return this._sentDate;
    }

    set sentDate(value: string) {
        this._sentDate = value;
    }
}