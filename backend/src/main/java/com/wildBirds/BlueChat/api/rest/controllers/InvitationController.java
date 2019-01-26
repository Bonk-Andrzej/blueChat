package com.wildBirds.BlueChat.api.rest.controllers;

import com.wildBirds.BlueChat.api.rest.dto.FriendsDto;
import com.wildBirds.BlueChat.api.rest.dto.InvitationDto;
import com.wildBirds.BlueChat.domain.model.InvitationFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/invitation")
public class InvitationController {

    private InvitationFacade invitationFacade;


    public InvitationController(InvitationFacade invitationFacade) {
        this.invitationFacade = invitationFacade;
    }

    @CrossOrigin
    @PostMapping("/invite")
    public ResponseEntity invite(@RequestBody InvitationDto invitationDto){

        invitationDto.setDateInvitation(Instant.now());
        InvitationDto response = invitationFacade.saveInvitation(invitationDto);

        return new ResponseEntity(response, HttpStatus.OK);

    }

    @CrossOrigin
    @PostMapping("/accept")
    public ResponseEntity acceptInvitation(@RequestBody InvitationDto invitationDto){
        System.out.println("AAAAAAAAAAAAAAAAAA");

        FriendsDto friendsDto = invitationFacade.acceptInvitation(invitationDto);

        return new ResponseEntity(friendsDto, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping({"{idUser}"})
    public ResponseEntity getInvitations(@PathVariable String idUser){

        List<InvitationDto> response = invitationFacade.getUserInvitation(Long.valueOf(idUser));


    return new ResponseEntity(response, HttpStatus.OK);
    }
}
