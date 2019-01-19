import {PhotoDto} from '../photo/photoDto';

export class UserDtoShort {
    public idUser: number;
    public nick: string;
    public photo: PhotoDto;

    constructor() {
    }
}