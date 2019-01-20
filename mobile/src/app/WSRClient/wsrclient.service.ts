import {Injectable} from '@angular/core';
import {WSRConnector} from "./wsrModule/api/WSRConnector";
import {WSRClient} from "./wsrModule/api/WSRClient";
import {ErrorDTO} from "./dto/ErrorDTO";
import {LocalType} from "./types/LocalType";
import {RemoteType} from "./types/RemoteType";


@Injectable({
    providedIn: 'root'
})
export class WSRClientService {

  private readonly wsrClient: WSRClient<LocalType,RemoteType>;

  constructor() {

    let wsrConnector = new WSRConnector<LocalType,RemoteType>();
    this.wsrClient = wsrConnector.connect("ws://localhost:8080/socket");


    this.wsrClient.addProcedure(LocalType.ERROR, new ErrorDTO(),data => {
        console.error(data,"Error");
    })
  }

  public get WRSClient(): WSRClient<LocalType,RemoteType>{
    return this.wsrClient;
  }
}
