package com.txt.security.registration.service.impl;

import com.txt.security.registration.entity.authen.Users;
import com.txt.security.registration.listener.OnRegistrationCompleteEvent;
import com.txt.security.registration.service.EmailService;
import com.txt.security.registration.util.CommonUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final MessageSource messages;
    private final Environment env;
    private final JavaMailSender mailSender;

    @Value("${urls.fe-host-path}")
    private String feHostPath;

    @Override
    public void sendMailConfirmRegistration(OnRegistrationCompleteEvent event, Users user, String token) {
        final SimpleMailMessage email = constructEmailConfirmRegistration(event, user, token);
        mailSender.send(email);
    }

    @Override
    public void sendMailResetPassword(HttpServletRequest request, Users user, String token) {
        mailSender.send(constructEmailResetToken(CommonUtils.getAppUrl(request), request.getLocale(), token, user));
    }

    @Override
    public void sendMailResendRegistrationToken(HttpServletRequest request, Users user, String token) {
        mailSender.send(constructResendVerificationTokenEmail(CommonUtils.getAppUrl(request), request.getLocale(), token, user));
    }

    private SimpleMailMessage constructEmailConfirmRegistration(final OnRegistrationCompleteEvent event, final Users user, final String token) {
        final String subject = "Registration Confirmation";
        final String confirmationUrl = feHostPath + "/registration-confirm?token=" + token;
        final String message = messages.getMessage("message.regSuccLink", null, "You registered successfully. To confirm your registration, please click on the below link.", event.getLocale());
        String body = message + " \r\n" + confirmationUrl;
        return constructEmail(subject, body, user);
    }

    private SimpleMailMessage constructEmailResetToken(String appUrl, Locale locale, String token, Users user) {
        final String url = feHostPath + "/update-password?token=" + token;
        final String message = messages.getMessage("message.resetPassword", null, locale);
        return constructEmail("Reset Password", message + " \r\n" + url, user);
    }

    private SimpleMailMessage constructResendVerificationTokenEmail(final String contextPath, final Locale locale, final String token, final Users user) {
        final String confirmationUrl = feHostPath + "/resend-registration-token?token=" + token;
        final String message = messages.getMessage("message.resendToken", null, locale);
        return constructEmail("Resend Registration Token", message + " \r\n" + confirmationUrl, user);
    }

    private SimpleMailMessage constructEmail(String subject, String body, Users user) {
        final String recipientAddress = user.getEmail();
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(recipientAddress);
        email.setFrom(env.getProperty("support.email"));
        return email;
    }

}
