import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {MessageDTO} from './messageDTO';
import {MessageToSentDTO} from './messageToSentDTO';
import {UserDTO} from '../user-repository/user-d-t.o';


@Injectable({
    providedIn: 'root'
})
export class MessagesRepositoryService {
    private http: HttpClient;
    private readonly host: string;

    constructor(http: HttpClient) {
        this.http = http;
        // this.host = 'http://51.38.133.76:90/messages';
        this.host = 'http://localhost:8099/messages';
    }

    private getHeaders(): HttpHeaders {
        const headers = new HttpHeaders();
        headers.set('Content-Type', 'application/json');
        headers.set('Access-Control-Allow-Origin', '*');
        headers.set('Access-Control-Allow-Origin', 'true');
        return headers;
    }

    public getAllMessages(): Observable<Array<MessageDTO>> {
        const headers = this.getHeaders();
        return this.http.get<Array<MessageDTO>>(this.host, {headers: headers});
    }

    public postMessages(messageSEND: MessageToSentDTO): Observable<MessageToSentDTO> {
        const headers = this.getHeaders();
        console.log(JSON.stringify(messageSEND));
        console.log(messageSEND);
        const messageToSend = {
            content: messageSEND.content,
            idReceiver: messageSEND.idReceiver,
            idSender: messageSEND.idSender
        };

        return this.http.post<MessageToSentDTO>(this.host, messageToSend, {headers: headers});
    }

    public getMessages(sender: UserDTO, receiver: UserDTO, startBound: number, toBound: number): Observable<Array<MessageDTO>> {
        const headers = this.getHeaders();
        //todo Have to implement in backend
        return this.http.get<Array<MessageDTO>>(
            this.host + '/by/' + sender.idUser + ',' + receiver.idUser + '/' + startBound + ',' + toBound, {headers: headers});
    }


    public getConversation(sender: UserDTO, receiver: UserDTO, limit: number, toBound: number): Observable<Array<MessageDTO>> {
        const headers = this.getHeaders();
        const params = new HttpParams()
            .set('idSender', sender.idUser.toString())
            .set('idReceiver', receiver.idUser.toString())
            .set('limit', limit.toString())
            .set('toBound', toBound.toString());

        return this.http.get<Array<MessageDTO>>(
            this.host + '/conversation/', {headers: headers, params: params});
    }
}
