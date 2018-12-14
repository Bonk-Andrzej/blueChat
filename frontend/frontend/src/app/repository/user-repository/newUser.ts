export class NewUser {
    public nick: string;
    public password: string;
    constructor(nick: string, password: string) {
        this.nick = nick;
        this.password = password;
    }
}