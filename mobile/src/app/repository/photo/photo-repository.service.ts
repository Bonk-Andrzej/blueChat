import {Injectable} from '@angular/core';
import {PhotoDto} from './photoDto';

@Injectable({
    providedIn: 'root'
})
export class PhotoRepositoryService {

    private photo: PhotoDto = new PhotoDto();

    // color: string;

    ngOnInit() {
        // this.randomColorTrigger();
    }

    randomColorTrigger(): string {

        return ('rgb(' + Math.floor(Math.random() * 255)
            + ',' + Math.floor(Math.random() * 255) + ','
            + Math.floor(Math.random() * 255) + ')');
    }

    public getRandomPhoto(): PhotoDto {
        this.photo.photo = this.randomColorTrigger();
        return new PhotoDto();
    }
}
