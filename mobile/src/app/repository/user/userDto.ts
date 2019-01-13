import {PhotoDto} from '../photo/photoDto';

export class UserDto {
    private id : number;
    private name: string;
    private email: string;
    private description: string;
    private photo : PhotoDto;
}