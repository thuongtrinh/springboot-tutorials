package com.txt.security.registration.service;

import com.txt.security.registration.entity.authen.Users;
import com.txt.security.registration.listener.OnRegistrationCompleteEvent;
import jakarta.servlet.http.HttpServletRequest;

public interface EmailService {

    void sendMailConfirmRegistration(OnRegistrationCompleteEvent event, Users user, String token);

    void sendMailResetPassword(HttpServletRequest request, Users user, String token);

    void sendMailResendRegistrationToken(HttpServletRequest request, Users user, String token);
}
