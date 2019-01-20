import {Procedure} from "../model/Procedure";

export interface ProcedureRepository<LT extends string> {

    addProcedure(procedure: Procedure<LT,any>);

    removeProcedure(procedureType: LT): void;

    getProcedure(procedureType: LT): Procedure<LT,any>;

    getLocalTypeData();
}