export class ChannelMessageDto {
    private _id: number;
    private _content: string;
    private _sentDate: string;
    private _idSender: string;
    private _idReceiver: number;

    constructor(id: number, content: string, sentDate: string, idSender: string, idReceiver: number) {
        this._id = id;
        this._content = content;
        this._sentDate = sentDate;
        this._idSender = idSender;
        this._idReceiver = idReceiver;
    }

    get id(): number {
        return this._id;
    }

    set id(value: number) {
        this._id = value;
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

    get idSender(): string {
        return this._idSender;
    }

    set idSender(value: string) {
        this._idSender = value;
    }

    get idReceiver(): number {
        return this._idReceiver;
    }

    set idReceiver(value: number) {
        this._idReceiver = value;
    }
}