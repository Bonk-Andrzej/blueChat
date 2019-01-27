import {Component, OnInit} from '@angular/core';
import {SearchService} from '../../services/search.service';
import {Observable} from 'rxjs';
import {UserDtoShort} from '../../repository/user/userDtoShort';
import {ChannelDtoShort} from '../../repository/channel/channelDtoShort';
import {Router} from '@angular/router';

@Component({
    selector: 'app-search',
    templateUrl: './search.component.html',
    styleUrls: ['./search.component.scss']
})
export class SearchComponent implements OnInit {


    users: Observable<Array<UserDtoShort>>;
    channels: Observable<Array<ChannelDtoShort>>;
    searchValue: string;

    constructor(private searchService: SearchService,
                private router: Router) {
    }


    ngOnInit() {
        this.users = this.searchService.getUsers()
        this.channels = this.searchService.getchannels()

    }
    public search() {
        this.searchService.search(this.searchValue);
    }

    private showUserProfile(idUser: number) {
        this.router.navigateByUrl('/user-profile/' + idUser).catch();
    }

    private showChannelProfile(idChannel: number) {
        this.router.navigateByUrl('/user-profile/' + idChannel).catch();
    }
}
