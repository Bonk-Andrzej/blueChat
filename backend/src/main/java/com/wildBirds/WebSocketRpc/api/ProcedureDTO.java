package com.wildBirds.WebSocketRpc.api;

public class ProcedureDTO<LT extends Enum<LT>,D> {
    LT type;
    D data;

    public ProcedureDTO(LT type, D data) {
        this.type = type;
        this.data = data;
    }

    public ProcedureDTO() {
    }

    public LT getType() {
        return type;
    }

    public void setType(LT type) {
        this.type = type;
    }

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }
}
