import { Injectable } from '@angular/core';
import {UserRepositoryService} from '../repository/user/user-repository.service';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private userReppository: UserRepositoryService) { }
}
