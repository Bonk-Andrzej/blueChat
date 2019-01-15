package com.wildBirds.BlueChat.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
class ChannelsMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChannelsMessage;
    private String content;

    private Instant sentDate;

    @ManyToOne
    @JoinColumn(name = "sender", nullable = false)
    private User sender;

    @ManyToOne
    @JoinColumn(name = "channelId", nullable = false)
    private Channel channel;


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name ="USER_READ_CHANNEL_MESSAGE",
            joinColumns = {@JoinColumn(name = "idChannel")},
            inverseJoinColumns = {@JoinColumn(name = "idUser")})
    private Set<User> usersChannelsMessages = new HashSet<>();

}
