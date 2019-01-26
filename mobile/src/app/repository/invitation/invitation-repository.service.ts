import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {environment} from '../../environment';
import {InvitationDto} from './invitationDto';

@Injectable({
  providedIn: 'root'
})
export class InvitationRepositoryService {
    private http: HttpClient;
    private headers: HttpHeaders;
    private readonly host: string;

    constructor(http: HttpClient) {
        this.http = http;
        this.host = environment.host + '/invitation';
        this.headers = this.getHeaders();
    }

    private getHeaders(): HttpHeaders {
        const headers = new HttpHeaders();
        headers.set('Content-Type', 'application/json');
        headers.set('Access-Control-Allow-Origin', '*');
        headers.set('Access-Control-Allow-Origin', 'true');
        return headers;
    }

    public invite(invitationDto: InvitationDto) : Promise<InvitationDto>{

      return this.http.post<InvitationDto>(this.host + '/invite', invitationDto, {headers: this.headers}).toPromise();
    }
    public accept(invitationDto: InvitationDto) : Promise<InvitationDto>{

        return this.http.post<InvitationDto>(this.host + '/accept', invitationDto, {headers: this.headers}).toPromise();
    }

    public getInvitations(idUser: number): Promise<Array<InvitationDto>>{
      return this.http.get<Array<InvitationDto>>(this.host + idUser, {headers: this.headers}).toPromise();
    }

}