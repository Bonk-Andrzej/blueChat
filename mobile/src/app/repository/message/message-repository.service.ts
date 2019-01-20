import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {MessageDto} from './messageDto';

@Injectable({
    providedIn: 'root'
})
export class MessageRepositoryService {

    private http: HttpClient;
    private readonly host: string;
    private headers: HttpHeaders;

    constructor(http: HttpClient) {
        this.http = http;
        this.host = 'http://localhost:8080/messages/conversation';
        this.host = 'http://192.168.99.100:200/messages/conversation';
        this.headers = this.getHeaders();
    }

    private getHeaders(): HttpHeaders {
        const headers = new HttpHeaders();
        headers.set('Content-Type', 'application/json');
        headers.set('Access-Control-Allow-Origin', '*');
        headers.set('Access-Control-Allow-Origin', 'true');
        return headers;
    }

    public getConversation(idSender: number, idReceiver: number, limit: number, toBound: number) :Promise<Array<MessageDto>> {
        const params = new HttpParams()
            .set('idSender', idSender.toString())
            .set('idReceiver', idReceiver.toString())
            .set('limit', limit.toString())
            .set('toBound', toBound.toString());

        return this.http.get<Array<MessageDto>>(this.host, {params: params, headers: this.headers}).toPromise()

    }

}