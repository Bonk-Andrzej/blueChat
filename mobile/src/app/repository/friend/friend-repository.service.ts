import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {FriendsDto} from './friendsDto';
import {environment} from '../../environment';

@Injectable({
    providedIn: 'root'
})
export class FriendRepositoryService {
    private http: HttpClient;
    private headers: HttpHeaders;
    private readonly host: string;

    constructor(http: HttpClient) {
        this.http = http;
        this.host = environment.host + '/friends';
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
        console.log('ID W REPOZYTORIUM >>',friendsDto);
        // this.http.delete(this.host, {observe : "friendsDto", headers: this.headers});
        // this.http.put<FriendsDto>(this.host , friendsDto ,{headers: this.headers});
       // return this.http.patch(this.host , friendsDto, {headers: this.headers}).toPromise();
        return this.http.request('delete', this.host, { body: friendsDto}).toPromise();
    }
}