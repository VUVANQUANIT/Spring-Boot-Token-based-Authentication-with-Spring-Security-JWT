package com.example.spring_boot_token.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    public void sendOtpEmail(String to, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Your OTP Code");
        message.setText("Your OTP is: " + otp);
        message.setFrom("quancn27@gmail.com"); // hoặc để mặc định nếu cấu hình trong properties

        mailSender.send(message);
    }

    public void testSendMail() {
        sendOtpEmail("YOUR_EMAIL@gmail.com", "123456");
    }
}
