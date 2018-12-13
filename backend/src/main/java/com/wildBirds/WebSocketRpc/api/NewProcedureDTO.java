package com.wildBirds.WebSocketRpc.api;

public class NewProcedureDTO {
    private String nameOfProcedure;
    private String dataJson;

    public NewProcedureDTO() {
    }

    public String getNameOfProcedure() {
        return nameOfProcedure;
    }

    public void setNameOfProcedure(String nameOfProcedure) {
        this.nameOfProcedure = nameOfProcedure;
    }

    public String getDataJson() {
        return dataJson;
    }

    public void setDataJson(String dataJson) {
        this.dataJson = dataJson;
    }
}
