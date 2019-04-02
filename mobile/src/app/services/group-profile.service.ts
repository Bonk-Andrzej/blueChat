import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";
import {ChannelObs} from "./model/channelObs";
import {ChannelRepositoryService} from "../repository/channel/channel-repository.service";
import {ChannelDtoCreate} from '../repository/channel/channelDtoCreate';

@Injectable({
    providedIn: 'root'
})
export class GroupProfileService {

    private groupBehavior: BehaviorSubject<ChannelObs>;

    constructor(private channelRepository: ChannelRepositoryService) {
        this.groupBehavior = new BehaviorSubject(null);
    }

    public getGroup(groupId: number): Observable<ChannelObs> {
        let group = this.groupBehavior.getValue();
        if (group != null && group.getIdChannel() == groupId) {
            return this.groupBehavior.asObservable()
        } else {
            this.fetchGroup(groupId).catch()
            return this.groupBehavior.asObservable()
        }
    }

    public getCurrnetGroup(): Observable<ChannelObs> {
        return this.groupBehavior.asObservable()
    }

    public createChannel(channelDtoCreate: ChannelDtoCreate){
        this.channelRepository.addChannel(channelDtoCreate);
    }

    private async fetchGroup(groupId: number) {
        const channelDto = await this.channelRepository.getById(groupId);
        this.groupBehavior.subscribe(value => {
            console.warn(">>>", value)
        })
        this.groupBehavior.next(ChannelObs.create(channelDto));
    }
}
