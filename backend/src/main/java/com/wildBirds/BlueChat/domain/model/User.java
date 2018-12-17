package com.wildBirds.BlueChat.domain.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    @Column(unique = true)
    private String nick;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "sender", cascade = {CascadeType.PERSIST , CascadeType.MERGE})
    private List<Message> messageSender;

    @OneToMany(mappedBy = "receiver", cascade = {CascadeType.PERSIST , CascadeType.MERGE})
    private List<Message> messageReceiver;

    @OneToMany(mappedBy = "sender", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<ChannelsMessage> channelsMessages;

    @ManyToMany(mappedBy = "usersInChannel", cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    private Set<Channel> channelsStaffed;
}
