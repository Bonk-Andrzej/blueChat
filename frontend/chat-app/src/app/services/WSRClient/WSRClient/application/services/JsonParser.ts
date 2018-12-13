import {ProcedureDTO} from "../../domain/model/ProcedureDTO";

export class JsonParser<LT extends string> {

    public parse(json: string, dataObject: any) :any{
        console.log(json,dataObject)
        return  Object.assign(dataObject,JSON.parse(json))
    }

}