import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ChannelDtoShort} from './channelDtoShort';
import {ChannelDto} from './channelDto';
import {environment} from "../../environment";
import {Params} from '@angular/router';

@Injectable({
    providedIn: 'root'
})
export class ChannelRepositoryService {

    private http: HttpClient;
    private readonly host: string;
    private headers: HttpHeaders;

    constructor(http: HttpClient) {
        this.http = http;
        this.host = environment.host + '/channel';
        this.headers = this.getHeaders();
    }

    private getHeaders(): HttpHeaders {
        const headers = new HttpHeaders();
        headers.set('Content-Type', 'application/json');
        headers.set('Access-Control-Allow-Origin', '*');
        headers.set('Access-Control-Allow-Origin', 'true');
        return headers;
    }

    public getShortList(idUser: number): Promise<Array<ChannelDtoShort>> {

        return this.http.get<Array<ChannelDtoShort>>(this.host + '/shorts/'+ idUser).toPromise();
    }
    public getPublicList(): Promise<Array<ChannelDtoShort>> {
        return this.http.get<Array<ChannelDtoShort>>(this.host + '/shorts').toPromise();
    }

    public getById(id: number): Observable<ChannelDto> {
        // return this.http.get<ChannelDtoShort>(this.host + idUser);
        return this.http.get<ChannelDto>(this.host + '/' + id);
    }

    public addChannel(channelDto: ChannelDto): Observable<ChannelDto> {
        return this.http.post<ChannelDto>(this.host, channelDto, {headers: this.headers});
    }

    public deleteChannel(idChannel: number) {
        return this.http.delete(this.host + idChannel, {headers: this.headers});
    }

    public addUserToChannel(idUser: number, idChannel: number) {
        const params = new HttpParams()
            .set('idUser', idUser.toString())
            .set('idChannel', idChannel.toString());
        this.http.post(this.host + '/user', {headers: this.headers, params: params});
    }

    public removeUserFromChannel(idUser: number, idChannel: number) {
        const params = new HttpParams()
            .set('idUser', idUser.toString())
            .set('idChannel', idChannel.toString());
        this.http.delete(this.host + '/user', {headers: this.headers, params: params});
    }


}
