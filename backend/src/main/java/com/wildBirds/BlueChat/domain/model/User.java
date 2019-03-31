package com.wildBirds.BlueChat.domain.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
class User extends BaseEntity{

    public User(String nick, String password) {
        this.nick = nick;
        this.password = password;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    @Column(unique = true)
    private String nick;

    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private String email;

    private String description;

    @OneToOne
    @JoinColumn(name = "idPhoto")
    private Photo profilePhoto;

    @OneToMany(mappedBy = "sender")
    private List<Message> messageSender;

    @OneToMany(mappedBy = "receiver")
    private List<Message> messageReceiver;

    @OneToMany(mappedBy = "sender")
    private List<ChannelsMessage> channelsMessages;

    @OneToMany(mappedBy = "senderInvitation")
    private List<Invitation> userInvite;

    @OneToMany(mappedBy = "receiverInvitation")
    private List<Invitation> invitedBy;

    @ManyToMany(mappedBy = "usersInChannel")
    private Set<Channel> channelsStaffed;

    @OneToMany(mappedBy = "channelOwner")
    private Set<Channel> channelsOwner;

    @ManyToMany(mappedBy = "usersChannelsMessages")
    private Set<ChannelsMessage> readChannelsMessage;

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", nick='" + nick + '\'' +
                '}';
    }
}
