import {Injectable} from '@angular/core';
import {WSRConnector} from "./WSRClient/api/WSRConnector";
import {WSRClient} from "./WSRClient/api/WSRClient";
import {ErrorDTO} from "./dto/ErrorDTO";
import {LocalType} from "./types/LocalType";
import {RemoteType} from "./types/RemoteType";


@Injectable({
    providedIn: 'root'
})
export class WSRClientService {

  private wsrClient: WSRClient<LocalType,RemoteType>;

  constructor() {

    let wsrConnector = new WSRConnector<LocalType,RemoteType>();
    this.wsrClient = wsrConnector.connect("ws://192.168.99.100:90/socket");


    this.wsrClient.addProcedure(LocalType.ERROR, new ErrorDTO(),data => {
        console.error(data,"Error");
    })


  }

  public get WRSClient(): WSRClient<LocalType,RemoteType>{
    return this.wsrClient;
  }
}
