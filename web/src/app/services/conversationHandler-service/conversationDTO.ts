export class ConversationDTO {
    private _content: string;
    private _sentDate: string;
    private _idOwner: number;
    private _idReceiver: number;


    constructor(content: string, sentDate: string, idOwner: number, idReceiver: number) {
        this._content = content;
        this._sentDate = sentDate;
        this._idOwner = idOwner;
        this._idReceiver = idReceiver;
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

    get idOwner(): number {
        return this._idOwner;
    }

    set idOwner(value: number) {
        this._idOwner = value;
    }

    get idReceiver(): number {
        return this._idReceiver;
    }

    set idReceiver(value: number) {
        this._idReceiver = value;
    }
}