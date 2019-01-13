import { Injectable } from '@angular/core';
import {UserRepositoryService} from '../repository/user/user-repository.service';
import {ChannelRepositoryService} from '../repository/channel/channel-repository.service';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  constructor(private userRepository: UserRepositoryService,
              private channelRepository : ChannelRepositoryService) { }
}
