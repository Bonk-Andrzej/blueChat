package com.wildBirds.BlueChat.domain.model;

import com.wildBirds.BlueChat.api.rest.dto.ContactMessageDto;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ContactMessageFacade {

    private ContactMessageRepository repository;
    private ContactMessageService service;
    private EmailFacade emailFacade;


//    public ContactMessageFacade(ContactMessageRepository repository, ContactMessageService service) {
//        this.repository = repository;
//        this.service = service;
//    }

    public ContactMessageDto saveMessage(ContactMessageDto contactMessageDto) {

        String email = contactMessageDto.getEmail();
        String subject = "Text JET support - we receive email from you";
        String content = contactMessageDto.getMessage();
        emailFacade.sendSampleMessage(email, subject, content);

        ContactMessage contactMessage = service.toEntity(contactMessageDto);

        ContactMessage saveMessage = repository.save(contactMessage);
        return service.toDto(saveMessage);
    }

    public ContactMessageDto getMessage(Long id){
        ContactMessage contactMessage = repository.getOne(id);

        return service.toDto(contactMessage);
    }

    public List<ContactMessageDto> getAllOrderedByDateAsc(){
        List<ContactMessage> allByOrOrderBySentDateDesc = repository.findAllByOrderBySentDateDesc();
//        List<ContactMessage> allByOrOrderBySentDateDesc = repository.findAll();


        List<ContactMessageDto> contactMessageDtoList = allByOrOrderBySentDateDesc.stream()
                .map(contactMessage -> service.toDto(contactMessage))
                .collect(Collectors.toList());
        return contactMessageDtoList;
    }
}
