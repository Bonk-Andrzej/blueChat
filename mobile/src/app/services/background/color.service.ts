import {Injectable} from '@angular/core';
import {ColorObject} from './colorObject';
import {BehaviorSubject, Observable} from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class ColorService {

    public colors: Array<ColorObject> = [];
    private readonly currentBacground: BehaviorSubject<ColorObject> = new BehaviorSubject(new ColorObject("",""));

    constructor() {

        const red = new ColorObject('red', '#ed463b');
        const green = new ColorObject('green', '#89ed3b');
        const orange = new ColorObject('orange', '#ed553b');
        const yellow = new ColorObject('yellow', '#f2b134');
        const cyan: ColorObject = new ColorObject('cyan', '#068587');
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
        this.setDefaultColor(cyan);
    }

    private setDefaultColor(cyan: ColorObject) {
        let selectedColor = localStorage.getItem('color');
        if (selectedColor != null) {
            let object : ColorObject = JSON.parse(selectedColor)
            this.currentBacground.next(object);
        } else {
            this.setCurrentColor(cyan);
        }
    }

    public setCurrentColor(bgColor: ColorObject) {
        this.currentBacground.next(bgColor);
        localStorage.setItem('color',JSON.stringify(bgColor));
    }

    public getCurrentColor(): Observable<ColorObject> {
        return this.currentBacground.asObservable();
    }

    public getColor(name: string) : ColorObject{
        return this.colors.find(color => (color.name == name));
    }

    // public getWhiteColor() : Observable<ColorObject> {
    //     return new ColorObject('orange', '#ed553b').o;
    // }


}
