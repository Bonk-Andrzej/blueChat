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
class Chanel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChanel;
    private String name;

    @OneToMany(mappedBy = "chanel", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<GroupsMessage> groupsMessage;


}
