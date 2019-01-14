import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class FriendRepositoryService {
    private http: HttpClient;
    private headers: HttpHeaders;
    private readonly host: string;

    constructor(http: HttpClient) {
        this.http = http;
        this.host = 'http://192.168.99.100:90/channel';
        this.headers = this.getHeaders();
    }

    private getHeaders(): HttpHeaders {
        const headers = new HttpHeaders();
        headers.set('Content-Type', 'application/json');
        headers.set('Access-Control-Allow-Origin', '*');
        headers.set('Access-Control-Allow-Origin', 'true');
        return headers;
    }
}