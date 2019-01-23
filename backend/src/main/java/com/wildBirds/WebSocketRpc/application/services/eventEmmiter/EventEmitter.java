package com.wildBirds.WebSocketRpc.application.services.eventEmmiter;

import java.util.ArrayList;

public class EventEmitter<TYPE> {


    private ArrayList<EventEmitterHandler<TYPE>> subscribers = new ArrayList<>();


    public void subscribe(EventEmitterHandler<TYPE> event){
        this.subscribers.add(event);
    }

    public void emit(TYPE data){
        for (EventEmitterHandler<TYPE> subscriber : this.subscribers) {
            subscriber.execute(data);
        }
    }

}
