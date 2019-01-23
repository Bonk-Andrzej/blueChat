import {EventEmitter, Injectable} from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class LeftMenuServiceService {

    private isDisplay: boolean = false;
    public onToggle = new EventEmitter<boolean>();

    constructor() {
    }

    public toggle() {
        this.isDisplay = !this.isDisplay;
        this.onToggle.emit(this.isDisplay);
    }

}
