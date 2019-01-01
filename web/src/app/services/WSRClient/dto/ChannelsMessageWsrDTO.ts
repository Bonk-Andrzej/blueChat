export class ChannelsMessageWsrDTO {
    private _senderId: number;
    private _channelId: number;
    private _content: string;
    private _sentDate: string;

    constructor() {
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