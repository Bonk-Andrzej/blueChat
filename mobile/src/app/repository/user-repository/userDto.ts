import {PhotoDto} from '../photo-repository/photoDto';

export class UserDto {
    private id : number;
    private name: string;
    private email: string;
    private description: string;
    private photo : PhotoDto;
}