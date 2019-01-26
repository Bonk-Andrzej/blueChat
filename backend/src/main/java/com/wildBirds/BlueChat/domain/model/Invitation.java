package com.wildBirds.BlueChat.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
class Invitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInvitation;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "senderInvitation")
    private User senderInvitation;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "receiverInvitation")
    private User receiverInvitation;
    private Instant dateInvitation;


}
