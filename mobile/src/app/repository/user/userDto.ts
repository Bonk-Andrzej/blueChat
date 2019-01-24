import {PhotoDto} from '../photo/photoDto';

export class UserDto {
    public idUser : number;
    public nick: string;
    public email: string;
    public description: string;
    public photoDto : PhotoDto;

    constructor(id: number = 0, name: string = "", email: string = "", description: string = "", photo: PhotoDto = null) {
        this.idUser = id;
        this.nick = name;
        this.email = email;
        this.description = description;
        this.photoDto = photo;
    }

}