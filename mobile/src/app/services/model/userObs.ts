import {PhotoDto} from "../../repository/photo/photoDto";
import {UserDto} from "../../repository/user/userDto";
import {UserDtoShort} from '../../repository/user/userDtoShort';

export class UserObs {
    private idUser : number = 0;
    private nick: string = "";
    private email: string = "";
    private description: string = "";
    private photo : PhotoDto;

    constructor() {
        this.photo = new PhotoDto();
    }

    public static create(userDto: UserDto): UserObs{
        const user = new UserObs();
        user.idUser = userDto.idUser;
        user.nick = userDto.nick;
        user.email = userDto.email;
        user.description = userDto.description;
        user.photo = userDto.photoDto;
        return user;
    }

    public toUserDto(): UserDto{
        const dto = new UserDto();
        dto.idUser = this.idUser;
        dto.nick = this.nick;
        dto.email = this.email;
        dto.description = this.description;
        dto.photoDto = this.photo;

        return dto;
    }

    public toUserDtoShort() : UserDtoShort{
        const user = new UserDtoShort();
        user.idUser = this.idUser;
        user.nick = this.nick;
        user.active = false;
        user.photoDto = this.photo;
        return user;
    }

    public getIdUser(): number{
        return this.idUser;
    }
    public getNick():string{
        return this.nick;
    }
    public getEmail():string{
        return this.email;
    }
    public  getDescription(): string{
        return this.description;
    }
    public getPhoto(): PhotoDto{
        return this.photo;
    }


}