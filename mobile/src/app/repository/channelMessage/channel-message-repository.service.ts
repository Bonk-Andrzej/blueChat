import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ChannelMessageDto} from './channelMessageDto';
import {environment} from "../../environment";

@Injectable({
  providedIn: 'root'
})
export class ChannelMessageRepositoryService {

  private http: HttpClient;
  private headers: HttpHeaders;
  private readonly host: string;

    constructor(http: HttpClient) {
        this.http = http;
        this.host = environment.host + '/channelMessages';
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
