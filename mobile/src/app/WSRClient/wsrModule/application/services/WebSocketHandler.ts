import {ProcedureRepository} from '../../domain/ports/ProcedureRepository';
import {ProcedureDTO} from '../../domain/model/ProcedureDTO';
import {JsonParser} from './JsonParser';
import {EventEmitter} from '@angular/core';
import {NewProcedureDTO} from '../../domain/model/NewProcedureDTO';

export class WebSocketHandler<LT extends string, RT extends string> {

    private webSocket: WebSocket;
    private procedureRepository: ProcedureRepository<LT>;
    private jsonParser: JsonParser<LT>;

    private onOpenEvent: EventEmitter<Event> = new EventEmitter();
    private onCloseEvent: EventEmitter<CloseEvent> = new EventEmitter();
    private onErrorEvent: EventEmitter<Event> = new EventEmitter();

    private readonly serverUrl: string;

    constructor(url: string, procedureRepository: ProcedureRepository<LT>) {

        this.serverUrl = url;
        this.initializeConnection();

        this.procedureRepository = procedureRepository;
        this.jsonParser = new JsonParser();
    }

    private initializeConnection(){
        this.webSocket = new WebSocket(this.serverUrl);

        this.webSocket.onopen = this.onOpenHandler.bind(this);
        this.webSocket.onmessage = this.onMessageHandler.bind(this);
        this.webSocket.onclose = this.onCloseHandler.bind(this);
        this.webSocket.onerror = this.onErrorHandler.bind(this);
    }

    public sendData(procedureDTO: ProcedureDTO<RT, any>): void {

        const newProcedureDTO = new NewProcedureDTO();
        newProcedureDTO.setDataJson(JSON.stringify(procedureDTO.getData()));
        newProcedureDTO.setNameOfProcedure(procedureDTO.getType());

        const newProcedureDTOJson = JSON.stringify(newProcedureDTO);
        // console.log('PROCEDURE SEND TO BACKEND', newProcedureDTOJson, procedureDTO);
        this.webSocket.send(newProcedureDTOJson);
    }

    // Handler
    private onOpenHandler(event: Event): void {
        console.log('WEBSOCKET OPEN CONNECTION');
        this.onOpenEvent.emit(event);
    }
    private onMessageHandler(event: MessageEvent): void {

        // console.log(event.data);
        // try {

        const procedureDTO: NewProcedureDTO = this.jsonParser.parse(event.data, new NewProcedureDTO());
        const nameOfProcedure: String = procedureDTO.getNameOfProcedure();
        const dataJson = procedureDTO.getDataJson();

        const procedure = this.procedureRepository.getProcedure(<LT>nameOfProcedure);
        const data = this.jsonParser.parse(dataJson, procedure.getDataObject());
        procedure.getMethod()(data);

        // } catch (e) {
        //     console.warn(e);
        // }
    }

    private onCloseHandler(event: CloseEvent): void {

        setTimeout(() => {
            this.initializeConnection();
            console.log('WEBSOCKET RETRY CONNECTION')
        }, 2000);
        console.log('WEBSOCKET LOST CONNECTION')
        this.onCloseEvent.emit(event);
    }

    private onErrorHandler(event: Event): void {
        this.onOpenEvent.emit(event);
    }

    // Events
    public onOpen(): EventEmitter<Event> {
        return this.onOpenEvent;
    }

    public onClose(): EventEmitter<CloseEvent> {
        return this.onCloseEvent;
    }

    public onError(): EventEmitter<Event> {
        return this.onErrorEvent;
    }
}