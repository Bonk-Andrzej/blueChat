package com.wildBirds.BlueChat.api.controllers;

import com.wildBirds.BlueChat.domain.model.MessageFacade;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private MessageFacade messageFacade;
}
