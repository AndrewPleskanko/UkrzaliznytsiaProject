package com.example.demo;

import com.example.demo.services.EmailSenderService;
import org.junit.jupiter.api.Test;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class EmailSenderServiceTest {
    @Test
    void sendSimpleEmail_ShouldSendEmail() {
        // Arrange
        JavaMailSender mailSender = mock(JavaMailSender.class);
        EmailSenderService emailSenderService = new EmailSenderService(mailSender);

        String toEmail = "recipient@example.com";
        String subject = "Test Subject";
        String body = "Test Body";

        // Act
        emailSenderService.sendSimpleEmail(toEmail, subject, body);

        // Assert
        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }
}
