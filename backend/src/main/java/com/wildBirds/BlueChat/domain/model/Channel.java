package com.wildBirds.BlueChat.domain.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChannel;
    private String name;

    @OneToMany(mappedBy = "channel", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<ChannelsMessage> channelsMessage = new ArrayList<>();


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name ="CHANNEL_CONTAIN_USERS",
            joinColumns = {@JoinColumn(name = "idChannel")},
            inverseJoinColumns = {@JoinColumn(name = "idUser")})
    private List<User> usersInChannel = new ArrayList<>();



    @ManyToOne
    private User channelOwner;

    private boolean isPublic;

}
