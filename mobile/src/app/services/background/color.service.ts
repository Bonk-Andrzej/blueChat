import {Injectable} from '@angular/core';
import {ColorObject} from './colorObject';
import {BehaviorSubject, Observable} from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class ColorService {

    public colors: Array<ColorObject> = [];
    currentBacground: BehaviorSubject<string> = new BehaviorSubject(null);

    constructor() {

        const red = new ColorObject('red', '#ed463b');
        const green = new ColorObject('green', '#89ed3b');
        const orange = new ColorObject('orange', '#ed553b');
        const yellow = new ColorObject('yellow', '#f2b134');
        const cyan = new ColorObject('cyan', '#068587');
        const black_light = new ColorObject('black light', '#464646');
        const black = new ColorObject('black', '#323232');
        const black_dark = new ColorObject('black_dark', 'rgba(30, 30, 30, 0.95)');

        this.colors.push(red);
        this.colors.push(green);
        this.colors.push(orange);
        this.colors.push(yellow);
        this.colors.push(cyan);
        this.colors.push(black_light);
        this.colors.push(black);
        this.colors.push(black_dark);
        this.setDefoultColor(cyan);
    }

    private setDefoultColor(cyan) {
        let selectedColor = localStorage.getItem('color');
        if (selectedColor != null) {
            this.currentBacground.next(selectedColor);
        } else {
            this.setCurrentColor(cyan);
        }
    }

    public setCurrentColor(bgColor: ColorObject) {
        this.currentBacground.next(bgColor.color);
        localStorage.setItem("color",bgColor.color);

    }

    public getCurrentColor(): Observable<string> {
       return this.currentBacground.asObservable();
    }


}
