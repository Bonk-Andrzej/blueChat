package com.wildBirds.BlueChat.api.rest.dto;

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
        private Instant sentDate;
        private Long senderId;
        private Long receiverId;

        @Override
        public String toString() {
                return "MessageDto{" +
                        "idMessage=" + idMessage +
                        ", content='" + content + '\'' +
                        ", sentDate=" + sentDate +
                        ", senderId=" + senderId +
                        ", receiverId=" + receiverId +
                        '}';
        }
}
