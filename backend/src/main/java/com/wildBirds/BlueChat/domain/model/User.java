package com.wildBirds.BlueChat.domain.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    private String nick;
    private String password;

    @OneToMany(mappedBy = "sender", cascade = {CascadeType.PERSIST , CascadeType.MERGE})
    private List<Message> messageSender;

    @OneToMany(mappedBy = "receiver", cascade = {CascadeType.PERSIST , CascadeType.MERGE})
    private List<Message> messageReceiver;

    @OneToMany(mappedBy = "sender", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<ChannelsMessage> channelsMessages;


    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name ="USERS_IN_CHANNEL",
            joinColumns = {@JoinColumn(name = "idUser")},
            inverseJoinColumns = {@JoinColumn(name = "idChannel")})
    private List<Channel> channelsStaffed;
}
