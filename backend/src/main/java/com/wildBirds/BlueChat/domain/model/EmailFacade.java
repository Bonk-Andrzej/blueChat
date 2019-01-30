
package com.wildBirds.BlueChat.domain.model;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


public class EmailFacade {

    private JavaMailSender javaMailSender;

    public EmailFacade(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendSampleMessage(String email, String subject, String content){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(subject);
        message.setText(content);
        javaMailSender.send(message);
    }

    public SimpleMailMessage templateSimpleMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText("This is the test email template for your email:\n%s\n");
        return message;
    }
}