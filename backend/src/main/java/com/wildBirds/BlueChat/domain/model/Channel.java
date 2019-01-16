package com.wildBirds.BlueChat.domain.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private boolean isPublic;

    @OneToOne
    @JoinColumn(name = "idPhoto")
    private Photo profilePhoto;

    @OneToMany(mappedBy = "channel", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<ChannelsMessage> channelsMessage = new ArrayList<>();



    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name ="CHANNEL_CONTAIN_USERS",
            joinColumns = {@JoinColumn(name = "idChannel")},
            inverseJoinColumns = {@JoinColumn(name = "idUser")})
    private Set<User> usersInChannel = new HashSet<>();


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "channelOwner")
    private User channelOwner;


    public boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(boolean aPublic) {
        isPublic = aPublic;
    }
}
