export class MessageToSentDTO {

    private _content: string;

    private _idSender: number;
    private _idReceiver: number;


    constructor(content: string, idSender: number, idReceiver: number) {
        this._content = content;
        this._idSender = idSender;
        this._idReceiver = idReceiver;
    }

    get content(): string {
        return this._content;
    }

    set content(value: string) {
        this._content = value;
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
