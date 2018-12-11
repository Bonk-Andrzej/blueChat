package com.wildBirds.BlueChat.domain.model;

import com.wildBirds.BlueChat.api.dto.ChannelDto;
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
class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChanel;
    private String name;

    @OneToMany(mappedBy = "channel", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<ChannelsMessage> channelsMessage;

    @ManyToMany(mappedBy = "channelsStaffed")
    private List<User> usersInChannel;

    @ManyToOne
    private User channelOwner;

    private boolean isPublic;

}
