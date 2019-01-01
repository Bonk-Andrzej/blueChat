import {ProcedureRepository} from "../../domain/ports/ProcedureRepository";
import {ProcedureDTO} from "../../domain/model/ProcedureDTO";
import {JsonParser} from "./JsonParser";
import {EventEmitter} from "@angular/core";
import {NewProcedureDTO} from "../../domain/model/NewProcedureDTO";

export class WebSocketHandler<LT extends string, RT extends string> {

    private readonly webSocket: WebSocket;
    private procedureRepository: ProcedureRepository<LT>;
    private jsonParser: JsonParser<LT>;

    private onOpenEvent: EventEmitter<Event> = new EventEmitter();
    private onCloseEvent: EventEmitter<CloseEvent> = new EventEmitter();
    private onErrorEvent: EventEmitter<Event> = new EventEmitter();

    constructor(url: string, procedureRepository: ProcedureRepository<LT>) {

        this.webSocket = new WebSocket(url);
        this.procedureRepository = procedureRepository;
        this.jsonParser = new JsonParser();

        this.webSocket.onopen = (e) => {
            console.log("on open")

            this.onOpenEvent.emit(e)
        };
        this.webSocket.onmessage = (e) => this.onMessage(e);
        this.webSocket.onclose = (e) => this.onCloseEvent.emit(e);
        this.webSocket.onerror = (e) => this.onOpenEvent.emit(e);

    }


    private onMessage(event: MessageEvent): void {


        console.log(event.data)
        // try {

        const procedureDTO1: NewProcedureDTO = this.jsonParser.parse(event.data, new NewProcedureDTO());
        const nameOfProcedure: String = procedureDTO1.getNameOfProcedure();
        const dataJson = procedureDTO1.getDataJson();

        const procedure = this.procedureRepository.getProcedure(<LT>nameOfProcedure);
        const data = this.jsonParser.parse(dataJson, procedure.getDataObject());
        procedure.getMethod()(data);

        // } catch (e) {
        //     console.warn(e);
        // }
    }


    public sendData(procedureDTO: ProcedureDTO<RT, any>): void {


        const newProcedureDTO = new NewProcedureDTO();
        newProcedureDTO.setDataJson(JSON.stringify(procedureDTO.getData()));
        newProcedureDTO.setNameOfProcedure(procedureDTO.getType())

        const newProcedureDTOJson = JSON.stringify(newProcedureDTO);
        console.log("to send ", newProcedureDTOJson, procedureDTO);
        this.webSocket.send(newProcedureDTOJson);
    }

    public onOpen(): EventEmitter<Event> {
        return this.onOpenEvent;
    }

    public onClose(): EventEmitter<CloseEvent> {
        return this.onCloseEvent;
    }

    private onError(): EventEmitter<Event> {
        return this.onErrorEvent;
    }
}