package com.mypackage.servesmart.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import java.util.Base64;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmailWithQRCode(String email, String base64QR) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(email);
            helper.setSubject("Your Meal Booking QR Code");
            helper.setText("Please find your meal booking QR Code attached.");

            // Convert Base64 to Byte Array
            byte[] qrImage = Base64.getDecoder().decode(base64QR);
            InputStreamSource qrSource = new ByteArrayResource(qrImage);
            helper.addAttachment("QRCode.png", qrSource);

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email");
        }
    }
}
