package com.wildBirds.WebSocketRpc.application.services;

import com.wildBirds.WebSocketRpc.api.ProcedureDTO;
import com.wildBirds.WebSocketRpc.domain.model.Procedure;
import com.wildBirds.WebSocketRpc.domain.model.Session;
import com.wildBirds.WebSocketRpc.domain.ports.ProcedureRepository;

public class ProcedureExecutor<LT extends Enum<LT>,RT extends Enum<RT>> {

    public static <LT extends Enum<LT>> ProcedureExecutor configure(ProcedureRepository<LT> procedureRepository){
        return new ProcedureExecutor(procedureRepository);
    }

    private ProcedureRepository<LT> procedureRepository;

    private ProcedureExecutor(ProcedureRepository procedureRepository) {
        this.procedureRepository = procedureRepository;

    }

    public <I> void execute(ProcedureDTO<LT,?> procedureDTO, Session<RT,I> session){

        try{
            Procedure<LT> procedure = procedureRepository.getProcedure(procedureDTO.getType());
            procedure.getMethod().execute(procedureDTO.getData(),session);

        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }
}
