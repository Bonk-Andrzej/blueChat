import {ProcedureRepository} from "../domain/ports/ProcedureRepository";
import {Procedure} from "../domain/model/Procedure";

export class ProcedureRepositoryImp<LT extends string> implements ProcedureRepository<LT> {

    private proceduresMap: Map<LT, Procedure<LT,any>>
    private localTypeData: LT;

    constructor() {
        this.proceduresMap = new Map()
    }

    public getLocalTypeData(){
        return this.localTypeData;
    }

    addProcedure(procedure: Procedure<LT, any>) {
        this.localTypeData = procedure.getType();
        this.proceduresMap.set(procedure.getType(),procedure);
    }

    getProcedure(procedureType: LT): Procedure<LT, any> {
            if (this.proceduresMap.has(procedureType)) {
                return this.proceduresMap.get(procedureType);
            } else {
                throw `Procedure: ${procedureType} doesn't exist in Repository`;
            }
    }
    removeProcedure(procedureType: LT): void {
        this.proceduresMap.delete(procedureType)
    }
}