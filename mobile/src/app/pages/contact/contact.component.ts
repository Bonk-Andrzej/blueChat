import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {ContactService} from '../../services/contact.service';

@Component({
    selector: 'app-contact',
    templateUrl: './contact.component.html',
    styleUrls: ['./contact.component.scss']
})
export class ContactComponent implements OnInit {

    public email: string;
    public content: string;


    constructor(private router: Router,
                private contService: ContactService) {
    }

    ngOnInit() {
    }

    public send(){
        this.contService.saveMessage(this.email, this.content);
        this.email = "";
        this.content= "";
        alert("Thanks for contact")
    }

    public openGithub() {
        window.open('https://github.com/newBlueChat/blueChat', '_blank');
    }


}
