import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {UserPassDto} from './userPassDto';
import {UserDto} from './userDto';
import {Observable} from 'rxjs';
import {UserDtoShort} from './userDtoShort';
import {UserDtoWithMessage} from './userDtoWithMessage';
import {environment} from '../../environment';

@Injectable({
    providedIn: 'root'
})
export class UserRepositoryService {

    private http: HttpClient;
    private readonly host: string;
    private headers: HttpHeaders;

    constructor(http: HttpClient) {
        this.http = http;
        this.host = environment.host;
        this.headers == this.getHeaders();

    }

    private getHeaders(): HttpHeaders {
        const headers = new HttpHeaders();
        headers.set('Content-Type', 'application/json');
        headers.set('Access-Control-Allow-Origin', '*');
        headers.set('Access-Control-Allow-Origin', 'true');
        return headers;
    }

    public getUserDtoList(): Promise<Array<UserDto>> {
        return this.http.get<Array<UserDto>>(this.host + '/users').toPromise();
    }

    public findUserByPhrase(phrase: string): Promise<Array<UserDtoShort>> {
        return this.http.get<Array<UserDtoShort>>(this.host + '/users/short/' + phrase).toPromise();
    }

    public postNewUser(userPassDto: UserPassDto): Observable<UserPassDto> {
        return this.http.post<UserPassDto>(this.host + '/users', userPassDto, {headers: this.headers});
    }

    public resetPass(email: string) {
        return this.http.patch(this.host + '/users/pass', email, {headers: this.headers});
    }

    public async updateUser(userDto: UserDto): Promise<UserDto> {
        return this.http.patch<UserDto>(this.host + '/users', userDto, {headers: this.headers}).toPromise();
    }

    public getUserById(id: number): Promise<UserDto> {
        return this.http.get<UserDto>(this.host + '/users/' + id).toPromise();
    }

    public logInUser(userPassDto: UserPassDto): Promise<UserDto> {
        return this.http.post<UserDto>(this.host + '/rpc/login', userPassDto, {headers: this.headers}).toPromise();
    }

    public gerUserWithMessage(id: number): Promise<Array<UserDtoWithMessage>> {
        return this.http.get<Array<UserDtoWithMessage>>(this.host + '/users/noRead/' + id, {headers: this.headers}).toPromise();
    }
}
