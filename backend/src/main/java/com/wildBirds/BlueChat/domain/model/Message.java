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
class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMessage;
    private String content;
    private Instant sentDate;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "sender")
    private User sender;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "receiver")
    private User receiver;

}
