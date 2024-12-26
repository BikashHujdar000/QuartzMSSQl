package com.example.quartz.Services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
@Slf4j
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(String fromEmail, String toEmail, String subject, String body) {
        try {
            log.info("from email" +fromEmail);
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, StandardCharsets.UTF_8.toString());

            helper.setSubject(subject);
            helper.setText(body, true);
            helper.setFrom(fromEmail);
            helper.setTo(toEmail);
            mailSender.send(message);

        } catch (MessagingException e) {

            log.info(":::::::::MessageException: " + e.getMessage());
        }

    }

}
