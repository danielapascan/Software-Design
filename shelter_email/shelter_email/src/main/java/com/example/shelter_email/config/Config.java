package com.example.shelter_email.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.shelter_email.jms.MailMessageReceiverBean;
import com.example.shelter_email.jms.MessageReceiver;
import com.example.shelter_email.service.MailService;
import com.example.shelter_email.service.MailServiceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class Config {

    @Bean
    public MailService mailService(JavaMailSender javaMailSender) {
        return new MailServiceBean(javaMailSender);
    }

    @Bean
    public MessageReceiver mailMessageReceiver(MailService mailService, ObjectMapper objectMapper) {
        return new MailMessageReceiverBean(mailService, objectMapper);
    }
}

