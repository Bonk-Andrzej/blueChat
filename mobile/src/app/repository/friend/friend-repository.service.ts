import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {FriendsDto} from './friendsDto';

@Injectable({
    providedIn: 'root'
})
export class FriendRepositoryService {
    private http: HttpClient;
    private headers: HttpHeaders;
    private readonly host: string;

    constructor(http: HttpClient) {
        this.http = http;
        this.host = 'http://localhost:8080/friends';
        this.host = 'http://192.168.99.100:200/friends';
        this.headers = this.getHeaders();
    }

    private getHeaders(): HttpHeaders {
        const headers = new HttpHeaders();
        headers.set('Content-Type', 'application/json');
        headers.set('Access-Control-Allow-Origin', '*');
        headers.set('Access-Control-Allow-Origin', 'true');
        return headers;
    }

    public getFriendshipsList(idUser: number): Promise<Array<FriendsDto>> {
        const params = new HttpParams()
            .set('idUser', idUser.toString());
        return this.http.get<Array<FriendsDto>>(this.host, {params: params, headers: this.headers}).toPromise();
    }

    public removeFriendship(friendsDto: FriendsDto) {

    }

    public addFriendship(idUser: number, friendsDto: FriendsDto) {

    }
}