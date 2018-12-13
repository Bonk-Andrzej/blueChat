package com.wildBirds.WebSocketRpc.infrastructure;

import com.wildBirds.WebSocketRpc.domain.model.Procedure;
import com.wildBirds.WebSocketRpc.domain.ports.ProcedureNotExist;
import com.wildBirds.WebSocketRpc.domain.ports.ProcedureRepository;

import java.util.Map;
import java.util.TreeMap;

public class ProcedureRepositoryInMemory<LT extends Enum<LT>> implements ProcedureRepository<LT> {

    private Map<String,Procedure<LT>> procedureMap;


    public ProcedureRepositoryInMemory() {
        procedureMap = new TreeMap<>();
    }

    @Override
    public void addProcedure(Procedure<LT> procedure) {
        // TODO: 2018-11-01  Remove LOG
         System.out.println("ADD procedure to repot : "+procedure.getProcedureType().toString());
        procedureMap.put(procedure.getProcedureType().name(),procedure);
    }

    @Override
    public void removeProcedure(LT type) {
        if (procedureMap.containsKey(type.name())){
        procedureMap.remove(type.name());
        }else {
            throw new ProcedureNotExist("Procedure not Exist in Repository");
        }
    }

    @Override
    public Procedure<LT> getProcedure(LT type) {
        if (procedureMap.containsKey(type.name())){
            return procedureMap.get(type.name());
        }else {
            throw new ProcedureNotExist("Procedure not Exist in Repository. Type: "+type.name());
        }
    }
}
