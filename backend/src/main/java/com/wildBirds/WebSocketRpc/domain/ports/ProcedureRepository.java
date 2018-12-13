package com.wildBirds.WebSocketRpc.domain.ports;

import com.wildBirds.WebSocketRpc.domain.model.Procedure;

public interface ProcedureRepository<LT extends Enum<LT>> {

    void addProcedure(Procedure<LT> procedure);

    void removeProcedure(LT type);

    Procedure<LT> getProcedure(LT type);

}
