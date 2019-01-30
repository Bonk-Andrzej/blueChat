import {Injectable} from '@angular/core';
import {ContactMessageRepositoryService} from '../repository/contactMessage/contact-message-repository.service';
import {ContactMessageDto} from '../repository/contactMessage/contactMessageDto';

@Injectable({
    providedIn: 'root'
})
export class ContactService {

    constructor(private contRepository: ContactMessageRepositoryService) {


    }

    public saveMessage(email: string, content: string) : void {
        let message = new ContactMessageDto();
        message.email = email;
        message.message = content;
        this.contRepository.sendMessage(message);
    }

}
