import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";
import {ChannelObs} from "./model/channelObs";
import {ChannelRepositoryService} from "../repository/channel/channel-repository.service";
import {UserDtoShort} from "../repository/user/userDtoShort";
import {UserObs} from "./model/userObs";
import {UserShortObs} from "./model/userShortObs";

@Injectable({
    providedIn: 'root'
})
export class GroupProfileService {

    private groupBehavior: BehaviorSubject<ChannelObs>;

    constructor(private channelRepositoryService: ChannelRepositoryService) {
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

    private async fetchGroup(groupId: number) {
        const channelDto = await this.channelRepositoryService.getById(groupId);
        this.groupBehavior.subscribe(value => {
            console.warn(">>>", value)
        })
        this.groupBehavior.next(ChannelObs.create(channelDto));
    }
}
