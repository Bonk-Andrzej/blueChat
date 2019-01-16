package com.wildBirds.BlueChat.domain.model;

import com.wildBirds.BlueChat.api.rest.dto.ContactMessageDto;
import com.wildBirds.BlueChat.domain.model.exceptions.ContactMessageException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ContactMessageService {
    private Logger log = LoggerFactory.getLogger(ContactMessageService.class);

    public ContactMessage toEntity(ContactMessageDto contactMessageDto) {
        ContactMessage contactMessage = new ContactMessage();

        if (contactMessageDto.getIdContactMessage() == null) {

            contactMessage.setIdContactMessage(contactMessageDto.getIdContactMessage());
        }
        try {
            contactMessage.setEmail(contactMessageDto.getEmail());
            contactMessage.setMessage(contactMessageDto.getMessage());
            contactMessage.setSentDate(contactMessageDto.getSentDate());


        } catch (NullPointerException e) {
            e.printStackTrace();
            log.error("ContactMessage Service - Some field was null", e.getMessage());
            throw new ContactMessageException("Message Service - Some field was null");

        } catch (Exception e) {
            e.printStackTrace();
            log.error("ChannelsMessage Service Exception", e.getMessage());
            throw new ContactMessageException("Message Service Exception");
        }
        return contactMessage;
    }


    public ContactMessageDto toDto(ContactMessage contactMessage) {
        ContactMessageDto contactMessageDto = new ContactMessageDto();
        try {
            contactMessageDto.setIdContactMessage(contactMessage.getIdContactMessage());
            contactMessageDto.setEmail(contactMessage.getEmail());
            contactMessageDto.setMessage(contactMessage.getMessage());
            contactMessageDto.setSentDate(contactMessage.getSentDate());

        } catch (NullPointerException e) {
            e.printStackTrace();
            log.error("ChannelsMessage Service - Some field was null", e.getMessage());
            throw new ContactMessageException("Message Service - Some field was null");

        } catch (Exception e) {
            e.printStackTrace();
            log.error("ChannelsMessage Service Exception", e.getMessage());
            throw new ContactMessageException("Message Service Exception");
        }
        return contactMessageDto;
    }
}
