
export class AuthSessionDTO {
    private userId: number;

    constructor(){

    }

    public getUserId():number {
        return this.userId;
    }

    public setUserId(userId: number):void {
    this.userId = userId;
}
}