import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ChannelDtoShort} from './channelDtoShort';
import {ChannelDto} from './channelDto';
import {environment} from "../../environment";
import {ChannelDtoCreate} from './channelDtoCreate';

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

    public getById(id: number): Promise<ChannelDto> {
        return this.http.get<ChannelDto>(this.host + '/' + id,{headers: this.headers}).toPromise();
    }

    public addChannel(channelDto: ChannelDtoCreate): Promise<ChannelDtoCreate> {
        console.log('HANAL W REPOZYTORIUM ' + channelDto.publicChannel)
        return this.http.post<ChannelDtoCreate>(this.host  + '/addChannel' , channelDto, {headers: this.headers}).toPromise();
    }

    public deleteChannel(idChannel: number) {
        return this.http.delete(this.host+ idChannel, {headers: this.headers});
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

    public findUserByPhrase(phrase: String): Promise<Array<ChannelDtoShort>> {
        return this.http.get<Array<ChannelDtoShort>>(this.host + '/shorts/phrase/'+ phrase).toPromise();
    }


}
