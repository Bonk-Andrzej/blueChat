import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {FriendsDto} from './friendsDto';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FriendRepositoryService {
    private http: HttpClient;
    private headers: HttpHeaders;
    private readonly host: string;

    constructor(http: HttpClient) {
        this.http = http;
        this.host = 'http://192.168.99.100:90/friends';
        this.headers = this.getHeaders();
    }

    private getHeaders(): HttpHeaders {
        const headers = new HttpHeaders();
        headers.set('Content-Type', 'application/json');
        headers.set('Access-Control-Allow-Origin', '*');
        headers.set('Access-Control-Allow-Origin', 'true');
        return headers;
    }
    public getFriendshipsList(idUser : number): Observable<FriendsDto>{
      return null;
    }
    public removeFriendship(friendsDto: FriendsDto){

    }
    public addFriendship(friendsDto: FriendsDto){

    }
}