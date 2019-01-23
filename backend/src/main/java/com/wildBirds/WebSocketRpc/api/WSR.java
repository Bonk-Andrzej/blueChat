package com.wildBirds.WebSocketRpc.api;

import com.wildBirds.WebSocketRpc.application.services.ProcedureDTOConverter;
import com.wildBirds.WebSocketRpc.application.services.ProcedureExecutor;
import com.wildBirds.WebSocketRpc.application.services.WSRWebSocketHandler;
import com.wildBirds.WebSocketRpc.application.services.eventEmmiter.EventEmitter;
import com.wildBirds.WebSocketRpc.domain.model.Procedure;
import com.wildBirds.WebSocketRpc.domain.ports.ProcedureRepository;
import com.wildBirds.WebSocketRpc.domain.ports.SessionRepository;
import com.wildBirds.WebSocketRpc.infrastructure.ProcedureRepositoryInMemory;
import com.wildBirds.WebSocketRpc.infrastructure.SessionRepositoryInMemory;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;
import java.util.Map;

/**
 *
 * @param <LT> Local type
 * @param <RT> Remote type
 * @param <I> ID type
 */
public class WSR<LT extends Enum<LT>,RT extends Enum<RT>,I extends Comparable<I>> {

    private WSRWebSocketHandler wsrWebSocketHandler;
    private ProcedureRepository<LT> procedureRepository;
    private SessionRepository<RT,I> sessionRepository;

    private ProcedureDTOConverter<LT> procedureDTOConverter;
    private ProcedureExecutor<LT, RT> procedureExecutor;


    public final EventEmitter<Session<RT,I>> onCloseConnection;


    public WSR(Class<LT> localType, Class<RT> remoteType) {


        this.procedureRepository = new ProcedureRepositoryInMemory<>();
        this.sessionRepository = new SessionRepositoryInMemory<>();
        this.procedureDTOConverter = new ProcedureDTOConverter<>(procedureRepository,localType);
        this.procedureExecutor = ProcedureExecutor.configure(procedureRepository);
        this.wsrWebSocketHandler = WSRWebSocketHandler.configure(sessionRepository,procedureDTOConverter,procedureExecutor);
        this.onCloseConnection =  this.wsrWebSocketHandler.getOnClose();
    }
    /**
     *
     * @param procedureType ID of procedure
     * @param dataType data type pass to procedure
     * @param method  method to execute for WebClient
     * @param <D> ID procedure type
     */
    public <D> void addProcedure(LT procedureType, Class<D> dataType, ProcedureMethod<RT,I,D> method ){

        procedureType.name();

        System.out.println("Add procdure Facade");
        this.procedureRepository.addProcedure(new Procedure<>(procedureType,method,dataType));
    }

    public Session<RT,I> findSession(I id){
        return this.sessionRepository.getSession(id);
    }

    public TextWebSocketHandler getHandler(){
        return this.wsrWebSocketHandler;
    }

    public Map<I, com.wildBirds.WebSocketRpc.domain.model.Session<RT, I>> getAuthorizedSessionsIdentifications(){
        return this.sessionRepository.getAuthorizedSessionMap();
    }
    public List<Session<RT,I>> getAllAuthorizedSession(){
        return (List<Session<RT, I>>) this.sessionRepository.getAuthorizedSessionMap();
    }

}
