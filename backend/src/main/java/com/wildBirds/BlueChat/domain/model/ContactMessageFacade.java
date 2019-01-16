package com.wildBirds.BlueChat.domain.model;

import com.wildBirds.BlueChat.api.rest.dto.ContactMessageDto;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ContactMessageFacade {

    private ContactMessageRepository repository;
    private ContactMessageService service;


//    public ContactMessageFacade(ContactMessageRepository repository, ContactMessageService service) {
//        this.repository = repository;
//        this.service = service;
//    }

    public ContactMessageDto saveMessage(ContactMessageDto contactMessageDto) {

        ContactMessage contactMessage = service.toEntity(contactMessageDto);

        ContactMessage saveMessage = repository.save(contactMessage);
        return service.toDto(saveMessage);
    }

    public ContactMessageDto getMessage(Long id){
        ContactMessage contactMessage = repository.getOne(id);

        return service.toDto(contactMessage);
    }

    public List<ContactMessageDto> getAllOrderedByDateAsc(){
        List<ContactMessage> allByOrOrderBySentDateDesc = repository.findAllByOrderBySentDateAsc();


        List<ContactMessageDto> contactMessageDtoList = allByOrOrderBySentDateDesc.stream()
                .map(contactMessage -> service.toDto(contactMessage))
                .collect(Collectors.toList());
        return contactMessageDtoList;
    }
}
