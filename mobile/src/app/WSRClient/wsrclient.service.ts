import {Injectable} from '@angular/core';
import {WSRConnector} from './wsrModule/api/WSRConnector';
import {WSRClient} from './wsrModule/api/WSRClient';
import {ErrorDTO} from './dto/ErrorDTO';
import {LocalType} from './types/LocalType';
import {RemoteType} from './types/RemoteType';
import {BehaviorSubject} from 'rxjs';
import {environment} from "../environment";


@Injectable({
    providedIn: 'root'
})
export class WSRClientService {

    public isConnected = new BehaviorSubject(false);

    private readonly wsrClient: WSRClient<LocalType, RemoteType>;

    constructor() {

        let wsrConnector = new WSRConnector<LocalType, RemoteType>();
        this.wsrClient = wsrConnector.connect(environment.socketHost + '/socket');

        this.wsrClient.onClose().subscribe(() => {
            this.isConnected.next(false);

        });
        this.wsrClient.onOpen().subscribe(() => {
            this.isConnected.next(true);
        });

        this.wsrClient.addProcedure(LocalType.ERROR, new ErrorDTO(), data => {
            console.error(data, 'Error');
        });
    }

    public get WRSClient(): WSRClient<LocalType, RemoteType> {
        return this.wsrClient;
    }
}
