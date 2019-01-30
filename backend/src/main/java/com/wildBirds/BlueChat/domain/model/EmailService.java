package com.wildBirds.BlueChat.domain.model;


import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

class EmailService {

    private JavaMailSender javaMailSender;

    public EmailService() {
        this.javaMailSender = this.initializeJavaMailSender();
    }

    public JavaMailSender getJavaMailSender() {
        return javaMailSender;
    }

    private JavaMailSender initializeJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("text.jet.contact@gmail.com");
        mailSender.setPassword("kursySDA2018!");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}