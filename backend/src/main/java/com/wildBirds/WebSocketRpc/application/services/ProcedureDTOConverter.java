package com.wildBirds.WebSocketRpc.application.services;

import com.wildBirds.WebSocketRpc.api.NewProcedureDTO;
import com.wildBirds.WebSocketRpc.api.ProcedureDTO;
import com.wildBirds.WebSocketRpc.domain.model.Procedure;
import com.wildBirds.WebSocketRpc.domain.ports.ProcedureRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ProcedureDTOConverter<LT extends Enum<LT>> {

    private ObjectMapper mapper;
    private ProcedureRepository<LT> procedureRepository;
    private Class<LT> localType;

    public ProcedureDTOConverter(ProcedureRepository<LT> procedureRepository, Class<LT> localType) {
        this.localType = localType;
        this.procedureRepository = procedureRepository;
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);
        this.mapper = mapper;
    }

    public String toJsonString(ProcedureDTO<LT,?> procedure) throws JsonProcessingException {

        final Object data = procedure.getData();
        final LT type = procedure.getType();

        final String dataJson = this.mapper.writeValueAsString(data);

        final NewProcedureDTO newProcedureDTO = new NewProcedureDTO();
        newProcedureDTO.setDataJson(dataJson);
        newProcedureDTO.setNameOfProcedure(type.name());
        return this.mapper.writeValueAsString(newProcedureDTO);
    }
    public ProcedureDTO<LT,?> toProcedureDTO(String json ) throws IOException {

        final NewProcedureDTO newProcedureDTO = this.mapper.readValue(json, NewProcedureDTO.class);

        final String nameOfProcedure = newProcedureDTO.getNameOfProcedure();
        final LT procedureEnum = LT.valueOf(localType, nameOfProcedure);
        final String dataProcedureJson = newProcedureDTO.getDataJson();


        final Procedure<LT> procedure = this.procedureRepository.getProcedure(procedureEnum);
        final Object data = this.mapper.readValue(dataProcedureJson, procedure.getProcedureDataType());

        final ProcedureDTO<LT, Object> procedureDTOToRetur = new ProcedureDTO<>();
        procedureDTOToRetur.setData(data);
        procedureDTOToRetur.setType(procedureEnum);
        return procedureDTOToRetur;

    }

}
