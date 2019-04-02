import { Injectable } from '@angular/core';
import {ChannelRepositoryService} from '../repository/channel/channel-repository.service';
import {ChannelDtoCreate} from '../repository/channel/channelDtoCreate';

@Injectable({
  providedIn: 'root'
})
export class GroupManageService {

  constructor(private channelRepository: ChannelRepositoryService) {


  }

  public createChannel(channelDtoCreate: ChannelDtoCreate){

  }

}
