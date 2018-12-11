package com.wildBirds.BlueChat.api.dto;


import java.time.Instant;

public class MessageDto {

        private Long idMessage;
        private String content;
        private Instant sendDate;
        private UserDto idSender;
        private UserDto idReceiver;
}
