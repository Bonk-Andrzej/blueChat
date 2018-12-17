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
class ChannelsMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMessageGroup;

    @ManyToOne
    @JoinColumn(name = "sender")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "idChannel")
    private Channel channel;

    private String content;

    private Instant sentDate;



}
