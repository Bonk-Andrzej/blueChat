export class MessageDTO {
    private senderId: number;
    private receiverId: number;
    private content: string;
    private sentDate: string;

    constructor() {
    }

    public getSentData():string{
        return this.sentDate;
    }
    public setSentDate(date: string){
        this.sentDate = date;
    }

    public getSenderId(): number {
        return this.senderId
    }

    public setSenderId(senderId: number) {
        this.senderId = senderId;
    }

    public getReceiverId(): number {
        return this.receiverId;
    }

    public setReceiverId(recieverId: number) {
        this.receiverId = recieverId;
    }

    public getContent(): string {
        return this.content;
    }

    public setContent(content: string) {
        this.content = content;
    }

}