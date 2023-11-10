package com.example.demo;

import com.example.demo.services.EmailSenderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class Demo2Application {
    private final EmailSenderService senderService;
    @Value("${email.message.to}")
    private String toEmail;

    @Value("${email.message.subject}")
    private String subject;

    @Value("${email.message.body}")
    private String body;
    public Demo2Application(EmailSenderService senderService) {
        this.senderService = senderService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Demo2Application.class, args);
    }

  /*  @EventListener(ApplicationReadyEvent.class)
    public void triggerMail() {
        senderService.sendSimpleEmail(toEmail,
                subject,
                body);
    }*/
}
