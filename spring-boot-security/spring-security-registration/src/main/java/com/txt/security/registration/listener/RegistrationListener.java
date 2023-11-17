package com.txt.security.registration.listener;

import com.txt.security.registration.entity.authen.Users;
import com.txt.security.registration.service.EmailService;
import com.txt.security.registration.service.TokenVerifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    @Autowired
    private EmailService emailService;

    @Autowired
    private TokenVerifyService tokenVerifyService;

    @Override
    public void onApplicationEvent(final OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(final OnRegistrationCompleteEvent event) {
        final Users user = event.getUser();
        final String token = UUID.randomUUID().toString();
        tokenVerifyService.createVerificationTokenForUser(user, token);
        emailService.sendMailConfirmRegistration(event, user, token);
    }

}
