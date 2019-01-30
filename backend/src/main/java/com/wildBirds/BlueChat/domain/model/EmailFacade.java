
package com.wildBirds.BlueChat.domain.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


public class EmailFacade {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendSampleMessage(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("igorsowa@wp.pl");
        message.setSubject("email by properties");
        message.setText("some special text from properties");
        javaMailSender.send(message);
    }
}