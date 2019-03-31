import {Injectable} from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class ColorsService {

    colors = {
        '--red-light': '#ff5a4f',
        '--red': '#ed463b',
        '--red-dark': '#d93227',

        '--green-light': '#9dff4f',
        '--green': '#89ed3b',
        '--green-dark': '#75d727',

        '--orange-light': '#ff694f',
        '--orange': '#ed553b',
        '--orange-dark': '#d94127',

        '--yellow-light': '#ffc548',
        '--yellow': '#f2b134',
        '--yellow-dark': '#de9d20',

        '--cyan-light': '#239697',
        '--cyan': '#068587',
        '--cyan-dark': '#007173',

        '--white-light': '#ffffff',
        '--white': '#eaeaea',
        '--white-dark': '#d6d6d6',

        '--black-light': '#464646',
        '--black': '#323232',
        '--black-dark': 'rgba(30, 30, 30, 0.95)',

        '--back-shadow': 'rgba(0, 0, 0, 0.25)',
    };

    constructor() {
    }

    public getColor(name: string): string {
        let color = this.colors[name];
        if (color == null) {
            color = 'red';
            console.error('ColorServices: Color Not Exist');
        }
        return color;
    }

    public getRandomColor(): string {
        let num1: string = this.randomNum().toString();
        let num2: string = this.randomNum().toString();
        let num3: string = this.randomNum().toString();

        return 'rgb(' + num1 + ',' + num2 + ',' + num3 + ')';

    }

    private randomNum(): number {
        return Math.round(Math.random() * 255);
    }

}
