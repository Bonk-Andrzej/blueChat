import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {ChannelDTO} from './channelDTO';
import {Observable} from 'rxjs';
import {UserDTO} from '../user-repository/user-d-t.o';
import {NewUser} from '../user-repository/newUser';

@Injectable({
  providedIn: 'root'
})
export class ChannelRepositoryService {

    private http: HttpClient;
    private readonly host: string;

    constructor(http: HttpClient) {
        this.http = http;
        // this.host = 'http://51.38.133.76:90/users';
        this.host = 'http://localhost:8099/channel';
    }

    private getHeaders(): HttpHeaders {
        const headers = new HttpHeaders();
        headers.set('Content-Type', 'application/json');
        headers.set('Access-Control-Allow-Origin', '*');
        headers.set('Access-Control-Allow-Origin', 'true');
        return headers;
    }

    public getChannels(): Observable<Array<ChannelDTO>> {
        return this.http.get<Array<ChannelDTO>>(this.host);
    }

}