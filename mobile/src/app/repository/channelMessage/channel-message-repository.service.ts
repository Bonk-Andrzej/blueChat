import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ChannelMessageDto} from './channelMessageDto';

@Injectable({
  providedIn: 'root'
})
export class ChannelMessageRepositoryService {

  private http: HttpClient;
  private headers: HttpHeaders;
  private readonly host: string;

    constructor(http: HttpClient) {
        this.http = http;
        // this.host = 'http://localhost:8080/channelMessages';
        // this.host = 'http://192.168.99.100:200/channelMessages';
        this.host = 'http://51.38.133.76:200/channelMessages';
        this.headers = this.getHeaders();
    }

    private getHeaders(): HttpHeaders {
        const headers = new HttpHeaders();
        headers.set('Content-Type', 'application/json');
        headers.set('Access-Control-Allow-Origin', '*');
        headers.set('Access-Control-Allow-Origin', 'true');
        return headers;
    }

    public getConversation(idChannel: number, limit: number, toBound: number): Observable<Array<ChannelMessageDto>>{

      const params = new HttpParams()
          .set('idChannel', idChannel.toString())
          .set('limit', limit.toString())
          .set('toBound', toBound.toString());
      return this.http.get<Array<ChannelMessageDto>>(this.http + '/conversation/', {headers: this.headers, params: params});

    }

}
