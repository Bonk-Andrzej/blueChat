import {UserDTO} from '../user-repository/user-d-t.o';

export class ChannelDTO {
    private _idChannel: number;
    private _name: string;
    private _userIdChannelOwner: number;
    private _userList: Array<UserDTO>;
    private _isPublic: boolean;


    constructor(idChannel: number, name: string, userIdChannelOwner: number, userList: Array<UserDTO>, isPublic: boolean) {
        this._idChannel = idChannel;
        this._name = name;
        this._userIdChannelOwner = userIdChannelOwner;
        this._userList = userList;
        this._isPublic = isPublic;
    }




    get idChannel(): number {
        return this._idChannel;
    }

    set idChannel(value: number) {
        this._idChannel = value;
    }

    get name(): string {
        return this._name;
    }

    set name(value: string) {
        this._name = value;
    }

    get userIdChannelOwner(): number {
        return this._userIdChannelOwner;
    }

    set userIdChannelOwner(value: number) {
        this._userIdChannelOwner = value;
    }

    get userList(): Array<UserDTO> {
        return this._userList;
    }

    set userList(value: Array<UserDTO>) {
        this._userList = value;
    }

    get isPublic(): boolean {
        return this._isPublic;
    }

    set isPublic(value: boolean) {
        this._isPublic = value;
    }
}
