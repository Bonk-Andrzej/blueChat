import {EventEmitter, Injectable} from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class LeftMenuServiceService {

    private isDisplay: boolean = false;
    private onToggleEvent = new EventEmitter<boolean>();

    constructor() {
    }

    public toggle() {
        this.isDisplay = !this.isDisplay;
        this.onToggleEvent.emit(this.isDisplay);
    }

    public onToggle(func: (isDisplay: boolean) => void) {
        this.onToggleEvent.subscribe(func);
    }
}
