import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ChannelDTO} from '../channel-repository/channelDTO';
import {ChannelMessageDTO} from './channelMessageDTO';

@Injectable({
  providedIn: 'root'
})
export class ChannelsMessageService {

    private http: HttpClient;
    private readonly host: string;

    constructor(http: HttpClient) {
        this.http = http;
        // this.host = 'http://51.38.133.76:90/messages';
        this.host = 'http://localhost:8099/channelMessages';
    }

    private getHeaders(): HttpHeaders {
        const headers = new HttpHeaders();
        headers.set('Content-Type', 'application/json');
        headers.set('Access-Control-Allow-Origin', '*');
        headers.set('Access-Control-Allow-Origin', 'true');
        return headers;
    }

    public getConversation(channel: ChannelDTO, limit: number, toBound: number): Observable<Array<ChannelMessageDTO>> {
        const headers = this.getHeaders();
        const params = new HttpParams()
            .set('channelId', channel.idChannel.toString())
            .set('limit', limit.toString())
            .set('toBound', toBound.toString());

        return this.http.get<Array<ChannelMessageDTO>>(
            this.host , {headers: headers, params: params});
    }
}