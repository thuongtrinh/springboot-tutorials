package com.txt.security.registration.service.impl;

import com.txt.security.registration.common.Constants;
import com.txt.security.registration.entity.authen.*;
import com.txt.security.registration.repository.*;
import com.txt.security.registration.service.GroupService;
import com.txt.security.registration.service.TokenVerifyService;
import com.txt.security.registration.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class TokenVerifyServiceImpl implements TokenVerifyService {

    private final Environment env;
    private final UserService userService;
    private final UserRepository userRepository;
    private final GroupMembersRespository groupMembersRespository;
    private final GroupRespository groupRespository;
    private final UserLocationRepository userLocationRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final PasswordResetTokenRepository passwordResetTokenRepository;


    @Override
    public VerificationToken createVerificationTokenForUser(Users user, String token) {
        final VerificationToken verificationToken = new VerificationToken(token, user);
        verificationToken.setStatus(Constants.TOKEN_STATUS.ACTIVE);
        VerificationToken verificationResponse = verificationTokenRepository.save(verificationToken);
        return verificationResponse;
    }

    @Override
    public VerificationToken validateVerificationToken(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
        if (ObjectUtils.isEmpty(verificationToken) || !Constants.TOKEN_STATUS.ACTIVE.equals(verificationToken.getStatus())) {
            log.warn("Token Verification not found {}", token);
            return null;
        }

        final Users user = verificationToken.getUser();
        final Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpirydate().getTime() - cal.getTime().getTime()) <= 0) {
            log.warn("Token Verification over expiry date {}", token);
            if(verificationToken.getUser().getEnabled()) {
                verificationTokenRepository.delete(verificationToken);
            }
            return null;
        }

        user.setEnabled(true);
        user.setEmailverified(true);
        userRepository.save(user);

        //update status token
        verificationToken.setStatus(Constants.TOKEN_STATUS.DONE);
        verificationTokenRepository.save(verificationToken);

        return verificationToken;
    }

    @Override
    public void createPasswordResetTokenForUser(Users user, String token) {
        final PasswordResetToken myToken = new PasswordResetToken(token, user);
        passwordResetTokenRepository.save(myToken);
    }

    @Override
    public boolean validatePasswordResetToken(String token) {
        final PasswordResetToken passToken = passwordResetTokenRepository.findByToken(token);
        if (ObjectUtils.isEmpty(passToken) || isTokenExpired(passToken)) {
            return false;
        }
        return true;
    }

    @Override
    public Optional<Users> getUserByPasswordResetToken(String token) {
        return Optional.ofNullable(passwordResetTokenRepository.findByToken(token).getUser());
    }

    @Override
    public void changeUserPassword(Users user, String password) {
        user.setPassword(password); //passwordEncoder.encode(password));
        userRepository.save(user);
    }

    @Override
    public boolean checkIfValidOldPassword(Users user, String oldPassword) {
//        return passwordEncoder.matches(oldPassword, user.getPassword());
        return oldPassword.equals(user.getPassword());
    }

    @Override
    public VerificationToken generateNewVerificationToken(String token, String email) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);

        Users user;
        if (ObjectUtils.isEmpty(verificationToken)) {
            user = userService.findUserByEmail(email);
        } else {
            user = verificationToken.getUser();
        }

        if (ObjectUtils.isEmpty(user) || !user.getEmail().equals(email)) {
            log.warn("Not found pair of user and token - verification {}, {}", email, token);
            return null;
        } else if (user.getEnabled()) {
            log.warn("This user has verified: {} ", email);
            return null;
        }

        String newToken = UUID.randomUUID().toString();
        if (ObjectUtils.isEmpty(verificationToken)) {
            verificationToken = createVerificationTokenForUser(user, newToken);
        } else {
            verificationToken.updateToken(newToken);
            verificationToken = verificationTokenRepository.save(verificationToken);
        }

        return verificationToken;
    }

    @Override
    public Users getUserFromVerificationToken(String token) {
        final VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
        if (verificationToken != null) {
            return verificationToken.getUser();
        }
        return null;
    }

    private boolean isTokenExpired(PasswordResetToken passToken) {
        final Calendar cal = Calendar.getInstance();
        return passToken.getExpirydate().before(cal.getTime());
    }
}
