import { Injectable } from '@angular/core';
import {UserDTO} from '../../repository/user-repository/user-d-t.o';
import {ChannelDTO} from '../../repository/channel-repository/channelDTO';
import {ChannelMessageDTO} from '../../repository/channelsMessage-repository/channelMessageDTO';

@Injectable({
  providedIn: 'root'
})
export class ChannelsMessageWsrService {

  private sender: UserDTO;
  private channel: ChannelDTO;
  private messsages: Array<ChannelMessageDTO>;

  constructor() { }
}
