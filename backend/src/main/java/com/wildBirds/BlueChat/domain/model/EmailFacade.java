
package com.wildBirds.BlueChat.domain.model;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


public class EmailFacade {

    JavaMailSender javaMailSender;

    public EmailFacade(EmailService aEmailService) {
        this.javaMailSender = aEmailService.getJavaMailSender();
    }

    public void sendSampleMessage(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("igorsowa@wp.pl");
        message.setSubject("some subject");
        message.setText("some speciall text");
        javaMailSender.send(message);
    }
}