export class MessageDTO {
    private _id: number;
    private _content: string;
    private _sentDate: string;
    private _idSender: number;
    private _idReceiver: number;


    constructor(id: number, content: string, sentDate: string, idSender: number, idReceiver: number) {
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

    get sendDateasDate():Date{
        return new Date(parseInt(this.sentDate));
    }

    set sentDate(value: string) {
        this._sentDate = value;
    }

    get idSender(): number {
        return this._idSender;
    }

    set idSender(value: number) {
        this._idSender = value;
    }

    get idReceiver(): number {
        return this._idReceiver;
    }

    set idReceiver(value: number) {
        this._idReceiver = value;
    }
}
