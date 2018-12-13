package com.wildBirds.WebSocketRpc.domain.model;

import com.wildBirds.WebSocketRpc.api.ProcedureMethod;

public class Procedure<LT extends Enum<LT>> {
    LT procedureType;
    ProcedureMethod method;
    Class<?> procedureDataType;

    public Procedure(LT procedureType, ProcedureMethod method, Class<?> dataType) {
        this.procedureDataType = dataType;
        this.procedureType = procedureType;
        this.method = method;
    }

    public Class<?> getProcedureDataType() {
        return procedureDataType;
    }

    public LT getProcedureType() {
        return procedureType;
    }

    public ProcedureMethod getMethod() {
        return method;
    }
}
