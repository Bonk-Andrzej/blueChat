import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';

@Component({
    selector: '[app-object-with-x]',
    templateUrl: './object-with-x.component.html',
    styleUrls: ['./object-with-x.component.scss']
})
export class ObjectWithXComponent implements OnInit {


    @Input() objectName: string;
    @Input() photoColor: string;
    @Input() iconImg: string;

    constructor() {

    }

    ngOnInit() {
        this.iconImg = this.iconImg || "";
    }

    randomColorTrigger() {

        // this.randomColor = ('rgb(' + Math.floor(Math.random() * 255)
        //     + ',' + Math.floor(Math.random() * 255) + ','
        //     + Math.floor(Math.random() * 255) + ')');
    }

    // ngOnChanges(): void {
    //     this.iconImg = this.iconImg || "";
    // }


}
