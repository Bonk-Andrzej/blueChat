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
class GroupsMessage extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMessageGroup;

    @ManyToOne
    @JoinColumn(name = "idSender")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "idChanel")
    private Chanel chanel;

    private String content;

    private Instant sentDate;



}
