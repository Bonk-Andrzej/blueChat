package com.wildBirds.BlueChat.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter

class LastMessageAggregate {

    private Long id;
    private User sender;  //this 1 that 2
    private User receiver;//this. 2 that 1

    //    private User sender;  //this 2 that 1
//    private User receiver;//this.1 that 2
//
//    private User sender;  //this 2 that 2
//    private User receiver;//this.1 that 1
    private Message lastMessage;
    private Instant sentDate;

    public LastMessageAggregate(User sender, User receiver, Message lastMessage, Instant sentDate) {
        this.sender = sender;
        this.receiver = receiver;
        this.lastMessage = lastMessage;
        this.sentDate = sentDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LastMessageAggregate that = (LastMessageAggregate) o;


        if (that.sender.getIdUser().equals(this.receiver.getIdUser()) && that.receiver.getIdUser().equals(this.sender.getIdUser())) {

            return true;
        }
        if (this.sender.getIdUser().equals(that.sender.getIdUser()) && receiver.getIdUser().equals(that.receiver.getIdUser())) {

            return true;

        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int result = sender != null ? sender.hashCode() : 0;
        result = 31 * result + (receiver != null ? receiver.hashCode() : 0);
        result = 31 * result + (lastMessage != null ? lastMessage.hashCode() : 0);
        return result;
    }
}
