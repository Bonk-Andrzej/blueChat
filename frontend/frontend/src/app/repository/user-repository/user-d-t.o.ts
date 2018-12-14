export class UserDTO {
    private _idUser: number;
    private _nick: string;

    constructor(id: number, nick: string) {
        this._idUser = id;
        this._nick = nick;
    }


    get idUser(): number {
        return this._idUser;
    }

    set idUser(value: number) {
        this._idUser = value;
    }

    get nick(): string {
        return this._nick;
    }

    set nick(value: string) {
        this._nick = value;
    }
}
