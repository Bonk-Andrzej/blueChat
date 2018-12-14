import {WSRClient} from "./WSRClient";
import {WebSocketHandler} from "../application/services/WebSocketHandler";
import {ProcedureRepositoryImp} from "../infrastructure/ProcedureRepositoryImp";
import {ProcedureRepository} from "../domain/ports/ProcedureRepository";

export class WSRConnector<LT extends string,RT extends string> {

    public connect(url: string):WSRClient<LT,RT>{

        let procedureRepositoryImp: ProcedureRepository<LT> = new ProcedureRepositoryImp();
        let webSocketHandler = new WebSocketHandler(url,procedureRepositoryImp);
        
        return WSRClient.configure(webSocketHandler,procedureRepositoryImp);

    }
}