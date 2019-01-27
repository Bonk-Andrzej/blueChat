import { Injectable } from '@angular/core';
import {ColorObject} from './colorObject';

@Injectable({
  providedIn: 'root'
})
export class ColorService {

  colors : Array<ColorObject>;
  constructor() {

      const red = new ColorObject('red', '#ed463b') ;

      const green = new ColorObject('green','#89ed3b' );

      const orange = new ColorObject('orange','#ed553b' );

      const yellow = new ColorObject('yellow', '#f2b134';

      const cyan = new ColorObject('cyan', '#068587');

      const black_light=  new ColorObject('black light', '#464646');
      const black = new ColorObject('black', '#323232');
      const black_dark = new ColorObject('black_dark', 'rgba(30, 30, 30, 0.95)') ;

  }


}
