import {PhotoDto} from "../../repository/photo/photoDto";
import {UserDtoShort} from "../../repository/user/userDtoShort";
import {BehaviorSubject, Observable} from "rxjs";
import {p} from '@angular/core/src/render3';

export class UserShortObs {
    private idUser: number;
    private nick: string;
    private photoDto: PhotoDto;
    private active: BehaviorSubject<boolean>;

    constructor() {
        this.active = new BehaviorSubject<boolean>(false);
    }


    public static create(userDto: UserDtoShort): UserShortObs {
        const user = new UserShortObs();
        user.idUser = userDto.idUser;
        user.nick = userDto.nick;
        user.photoDto = userDto.photoDto;
        user.active.next(userDto.active);
        return user;

    }

    public getIdUser(): number {
        return this.idUser;
    }

    public getNick(): string {
        return this.nick;
    }

    public getPhoto(): PhotoDto {
        return this.photoDto;
    }

    public getActive(): boolean {
        return this.active.getValue()
    }

    public getActiveObs(): Observable<boolean>{
       return this.active.asObservable();
    }

    public setActive(isActive: boolean){
        this.active.next(isActive);
    }

    public setIdUser(idUser : number){
        this.idUser = idUser;
    }

    public setNick(nick: string)  {
        this.nick = nick;
    }

    public setPhoto(photoDto: PhotoDto){
         this.photoDto = photoDto;
    }

}