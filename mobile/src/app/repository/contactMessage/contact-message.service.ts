import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import {ContactMessageDto} from './contactMessageDto';

@Injectable({
  providedIn: 'root'
})
export class ContactMessageService {
    private http: HttpClient;
    private headers: HttpHeaders;
    private readonly host: string;

    constructor(http: HttpClient) {
        this.http = http;
        this.host = 'http://192.168.99.100:90/contact';
        this.headers = this.getHeaders();
    }

    private getHeaders(): HttpHeaders {
        const headers = new HttpHeaders();
        headers.set('Content-Type', 'application/json');
        headers.set('Access-Control-Allow-Origin', '*');
        headers.set('Access-Control-Allow-Origin', 'true');
        return headers;
    }

    public sendMessage(contactMessageDto: ContactMessageDto){
        return this.http.post<ContactMessageDto>(this.host, contactMessageDto,
            {headers: this.headers, observe: 'response'})
            .subscribe(
                (res: HttpResponse<any>) => {
                    console.log(res.headers.keys);
                }
            );
    }
}