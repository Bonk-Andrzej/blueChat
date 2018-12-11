package com.wildBirds.BlueChat.api.controllers;


import com.wildBirds.BlueChat.domain.model.UserFacade;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserFacade userFacade;
}
