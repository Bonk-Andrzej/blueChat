package com.wildBirds.BlueChat.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {

        private Long idMessage;
        private String content;
        private Instant sendDate;
        private UserDto idSender;
        private UserDto idReceiver;
}
