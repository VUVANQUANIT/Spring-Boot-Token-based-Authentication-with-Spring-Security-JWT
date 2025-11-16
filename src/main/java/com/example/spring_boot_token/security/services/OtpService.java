package com.example.spring_boot_token.security.services;

import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class OtpService {

    private StringRedisTemplate redisTemplate;

    private EmailService emailService;

    public OtpService(StringRedisTemplate redisTemplate, EmailService emailService) {
        this.redisTemplate = redisTemplate;
        this.emailService = emailService;
    }

    public void sendOtp(String email) {
        String otp = String.valueOf(new Random().nextInt(900000) + 100000);
        // Lưu OTP vào Redis với TTL 10 phút
        redisTemplate.opsForValue().set("otp:" + email, otp, 10, TimeUnit.MINUTES);
        // Gửi OTP qua email
        emailService.sendOtpEmail(email, otp);
    }

    public boolean verifyOtp(String email, String otp) {
        String key = "otp:" + email;
        String cachedOtp = redisTemplate.opsForValue().get(key);
        if (cachedOtp != null && cachedOtp.equals(otp)) {
            redisTemplate.delete(key);
            return true;
        }
        return false;
    }
}
