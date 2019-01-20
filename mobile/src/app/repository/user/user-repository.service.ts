import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {UserPassDto} from './userPassDto';
import {UserDto} from './userDto';
import {Observable} from 'rxjs';
import {UserDtoShort} from './userDtoShort';

@Injectable({
    providedIn: 'root'
})
export class UserRepositoryService {

    private http: HttpClient;
    private readonly host: string;
    private headers: HttpHeaders;

    constructor(http: HttpClient) {
        this.http = http;
        this.host = 'http://localhost:8080';
        this.host = 'http://192.168.99.100:200';
        this.headers == this.getHeaders();

    }

    private getHeaders(): HttpHeaders {
        const headers = new HttpHeaders();
        headers.set('Content-Type', 'application/json');
        headers.set('Access-Control-Allow-Origin', '*');
        headers.set('Access-Control-Allow-Origin', 'true');
        return headers;
    }

    public getUserDtoList(): Promise<Array<UserDto>>  {
        return this.http.get<Array<UserDto>>(this.host + '/users').toPromise();
    }

    // public getUserDtoShortList(): Observable<Array<UserDtoShort>> {
    //     return this.http.get<Array<UserDtoShort>>(this.host + '/users/short');
    // }

    public findUserByPhrase(phrase: string): Observable<Array<UserDtoShort>> {
        return this.http.get<Array<UserDtoShort>>(this.host + '/users/short');
    }

    public postNewUser(userPassDto: UserPassDto): Observable<UserPassDto> {
        return this.http.post<UserPassDto>(this.host + '/users', userPassDto, {headers: this.headers});
    }

    public resetPass(email: string) {
        return this.http.patch(this.host + '/users/pass', email, {headers: this.headers});
    }
    public updateUser(userDto: UserDto) : Observable<UserDto>{
        return this.http.patch<UserDto>(this.host + '/users', userDto, {headers: this.headers})
    }

    public getUserById(id: number): Promise<UserDto> {
        return this.http.get<UserDto>(this.host + '/users/' + id).toPromise();
    }

    public logInUser(userPassDto: UserPassDto): Promise<UserDto> {
        return this.http.post<UserDto>(this.host + '/rpc/login', userPassDto, {headers: this.headers}).toPromise();
    }
}
