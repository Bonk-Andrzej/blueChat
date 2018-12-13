import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {UserDTO} from './user-d-t.o';
import {NewUser} from './newUser';

@Injectable({
    providedIn: 'root'
})
export class UserRepositoryService {

    private http: HttpClient;
    private readonly host: string;

    constructor(http: HttpClient) {
        this.http = http;
        this.host = 'http://51.38.133.76:90/users';
        // this.host = 'http://localhost:8080/users';
    }

    private getHeaders(): HttpHeaders {
        const headers = new HttpHeaders();
        headers.set('Content-Type', 'application/json');
        headers.set('Access-Control-Allow-Origin', '*');
        headers.set('Access-Control-Allow-Origin', 'true');
        return headers;
    }

    public getUsers(): Observable<Array<UserDTO>> {
        return this.http.get<Array<UserDTO>>(this.host);
    }

    public postNewUser(newUser: NewUser): Observable<NewUser> {
        const headers = this.getHeaders();

        return this.http.post<NewUser>(this.host, {nick: newUser.nick, password: newUser.password, headers});
    }

    public getUserById(id: number): Observable<UserDTO> {
        return this.http.get<UserDTO>(this.host + id);
    }

    public repleaceUser(newUser: NewUser, id: number): Observable<NewUser> {
        const headers = this.getHeaders();
        return this.http.put<NewUser>(this.host, {nick: newUser.nick, password: newUser.password, headers});
    }

    public deleteUser(id: number) {

        return this.http.delete<UserDTO>(this.host + '/' + id, {headers: this.getHeaders()});
    }

    public getUserByNickPass(nick: string, pass: string) {
        const headers = this.getHeaders();
        const userDto: NewUser = new NewUser(nick, pass);
        return this.http.post(this.host + '/login', userDto);
    }
}

