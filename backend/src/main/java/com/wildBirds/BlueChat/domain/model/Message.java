package com.wildBirds.BlueChat.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
class Message extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMessage;
    private String content;
    private Instant sendDate;

    @ManyToOne
    @JoinColumn(name = "idSender")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "idReceiver")
    private User receiver;

}
